package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;

/**
 * A test that demonstrates there is no problem when binding a value which is a SpEL expression with `@Value`
 */
@SpringBootTest(properties = "property.task-executor=#{@demoTaskExecutor}")
class PropertiesSpelTest {

	@Test
	void propertiesSpelTest(@Value("${property.task-executor}") TaskExecutor taskExecutor) {
		Assertions.assertNotNull(taskExecutor);
	}
}