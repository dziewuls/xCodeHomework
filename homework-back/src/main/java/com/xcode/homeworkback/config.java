package com.xcode.homeworkback;

import com.xcode.homeworkback.domain.ExchangeRateFinderService;
import com.xcode.homeworkback.domain.NumbersSorterService;
import com.xcode.homeworkback.domain.StatusService;
import com.xcode.homeworkback.domain.port.CurrencyRepository;
import com.xcode.homeworkback.infrastructure.CurrencyNbpClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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
    CurrencyRepository currencyRepository() {
        return new CurrencyNbpClientRepository(new RestTemplate());
    }

    @Bean
    ExchangeRateFinderService exchangeRateFinderService(CurrencyRepository currencyRepository) {
        return new ExchangeRateFinderService(currencyRepository);
    }
}
