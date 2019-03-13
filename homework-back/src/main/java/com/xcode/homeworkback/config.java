package com.xcode.homeworkback;

import com.xcode.homeworkback.domain.ExchangeRateFinderService;
import com.xcode.homeworkback.domain.NumbersSorterService;
import com.xcode.homeworkback.domain.StatusService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {

    @Bean
    StatusService statusService() {
        return new StatusService();
    }

    @Bean
    NumbersSorterService numbersSorterService() {
        return new NumbersSorterService();
    }

    @Bean
    ExchangeRateFinderService exchangeRateFinderService() {
        return new ExchangeRateFinderService();
    }
}
