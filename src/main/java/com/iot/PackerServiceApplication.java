package com.iot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class PackerServiceApplication {
    protected static Logger logger = LoggerFactory.getLogger(PackerServiceApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(PackerServiceApplication.class, args);
    }
}
