package de.rieckpil.learning.springboot2book;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBeansConfiguration {

    @Bean
    public Foo foo() {
        return new Foo("Philip");
    }
}
