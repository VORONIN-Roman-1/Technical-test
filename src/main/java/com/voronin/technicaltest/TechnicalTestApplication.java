package com.voronin.technicaltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Class application.
 */
@SpringBootApplication
@PropertySource("classpath:user.properties")
public class TechnicalTestApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TechnicalTestApplication.class, args);
    }

}
