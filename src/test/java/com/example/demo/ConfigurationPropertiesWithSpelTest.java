package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * A test that demonstrates the EXCEPTION when binding a value which is a SpEL expression with {@link ConfigurationProperties}
 */
@SpringBootTest(properties = {
		"demo.app.name=${spring.application.name}",
		"demo.app.executor=#{@demoTaskExecutor}"
})
class ConfigurationPropertiesWithSpelTest {

	@Test
	void configurationPropertiesSpelTest(@Autowired DemoConfigConfigurationProperties properties) {
		Assertions.assertAll(
				() -> Assertions.assertNotNull(properties.getName(), "Values references work fine"),
				() -> Assertions.assertNotNull(properties.getExecutor(), "SpEL expressions don't work")
		);
	}
}