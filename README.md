# Overview

The project demonstrates the lack of `SpEL` support in `ConfigurationProperties` for the [#42608](https://github.com/spring-projects/spring-boot/issues/42608) issue in the Spring Boot Repository

There are three tests:

1. [`com.example.demo.PropertiesSpelTest`](https://github.com/neshkeev/lack-of-spel-configurationproperties/blob/e1891a8274d440e4174b06edcde94f0ed796080a/src/test/java/com/example/demo/PropertiesSpelTest.java) demonstrates that one can use SpEL for regular properties which can then be bound to a complex object without any converters
2. [`com.example.demo.ConfigurationPropertiesWithSpelTest`](https://github.com/neshkeev/lack-of-spel-configurationproperties/blob/e1891a8274d440e4174b06edcde94f0ed796080a/src/test/java/com/example/demo/ConfigurationPropertiesWithSpelTest.java) (_currently FAILS_) demonstrates the exception when one binds a SpEL expression in a `ConfigurationProperties` bean field. Notice that there are no problems with binding a regular reference to another property
3. [`com.example.demo.ConfigurationPropertiesWithSpelWorkaroundTest`](https://github.com/neshkeev/lack-of-spel-configurationproperties/blob/e1891a8274d440e4174b06edcde94f0ed796080a/src/test/java/com/example/demo/ConfigurationPropertiesWithSpelWorkaroundTest.java) demonstrates how to define a converter that overcomes the problems with SpEL expressions for `ConfigurationProperties` beans
