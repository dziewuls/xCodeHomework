package com.xcode.homeworkback;

import com.xcode.homeworkback.domain.StatusService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {

    @Bean
    StatusService statusService() {
        return new StatusService();
    }
}
