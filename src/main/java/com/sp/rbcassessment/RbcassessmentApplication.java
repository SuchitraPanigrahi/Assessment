package com.sp.rbcassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RbcassessmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(RbcassessmentApplication.class, args);
    }

}
