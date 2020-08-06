package dev.abhi.roiim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
public class RoiimApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoiimApplication.class, args);
    }

}
