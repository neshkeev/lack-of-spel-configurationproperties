package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.task.TaskExecutor;

@ConfigurationProperties(prefix = "demo.app")
public class DemoConfigConfigurationProperties {
    private String name;
    private TaskExecutor executor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(TaskExecutor executor) {
        this.executor = executor;
    }
}