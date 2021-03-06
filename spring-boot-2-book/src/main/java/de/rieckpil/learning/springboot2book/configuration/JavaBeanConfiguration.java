package de.rieckpil.learning.springboot2book.configuration;

import de.rieckpil.learning.springboot2book.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ServletComponentScan
public class JavaBeanConfiguration {

    @Bean
    public Foo foo2(@Value("${foo.name:n/a}") String name) {
        return new Foo(name);
    }

}

