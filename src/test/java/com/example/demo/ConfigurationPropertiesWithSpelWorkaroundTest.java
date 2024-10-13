package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.task.TaskExecutor;

/**
 * The test demonstrates a Workaround that helps to map a SpEL expression to an Object in {@link ConfigurationProperties}
 */
@SpringBootTest(properties = {
		"demo.app.name=${spring.application.name}",
		"demo.app.executor=#{@demoTaskExecutor}"
})
class ConfigurationPropertiesWithSpelWorkaroundTest {

	@Test
	void configurationPropertiesSpelTest(@Autowired DemoConfigConfigurationProperties properties) {
		Assertions.assertAll(
				() -> Assertions.assertNotNull(properties.getName(), "Values references work fine"),
				() -> Assertions.assertNotNull(properties.getExecutor(), "SpEL expressions don't work")
		);
	}

	@TestConfiguration
	static class Config {
		@Bean
		public ConversionService conversionService(DefaultListableBeanFactory beanFactory) {
			final var conversionService = new DefaultConversionService();
			conversionService.addConverter(new StringTaskExecutorConverter(beanFactory));
			return conversionService;
		}

		private record StringTaskExecutorConverter(
				DefaultListableBeanFactory beanFactory
		) implements Converter<String, TaskExecutor> {

			@Override
			public TaskExecutor convert(@SuppressWarnings("NullableProblems") String source) {
				final var beanExpressionResolver = beanFactory.getBeanExpressionResolver();
				if (beanExpressionResolver == null) return null;

				final var expressionContext = new BeanExpressionContext(beanFactory, new SimpleThreadScope());

				return (TaskExecutor) beanExpressionResolver.evaluate(source, expressionContext);
			}
		}
	}
}