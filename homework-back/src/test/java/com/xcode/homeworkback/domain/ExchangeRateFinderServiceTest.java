package com.xcode.homeworkback.domain;

import com.xcode.homeworkback.domain.model.Currency;
import com.xcode.homeworkback.domain.port.CurrencyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeRateFinderServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    private ExchangeRateFinderService exchangeRateFinderService;
    private List<Currency> currenciesFromRepository;

    @Before
    public void initialExchangeRateFinderService() {
        this.exchangeRateFinderService = new ExchangeRateFinderService(currencyRepository);
    }

    @Before
    public void initCurrenciesFromRepository() {
        this.currenciesFromRepository = new ArrayList<>(Arrays.asList(
                new Currency("EUR", 4.3006),
                new Currency("HUF", 0.013664)
        ));
    }

    @Test
    public void shouldReturnExpectedValue() {
        when(this.currencyRepository.findAllCurrenciesExchangeRates())
                .thenReturn(this.currenciesFromRepository);

        Double expectedValue = 4.3006;
        String currencyCode = "EUR";

        Double result = this.exchangeRateFinderService.findExchangeRate(currencyCode);

        assertThat(result).isEqualTo(expectedValue);
    }

    @Test
    public void shouldReturnZeroWhenCurrencyNotFound() {
        when(this.currencyRepository.findAllCurrenciesExchangeRates())
                .thenReturn(this.currenciesFromRepository);

        Double expectedValue = 0.0;
        String currencyCode = "some";

        Double result = this.exchangeRateFinderService.findExchangeRate(currencyCode);

        assertThat(result).isEqualTo(expectedValue);
    }

    @Test
    public void shouldReturnZeroWhenCurrencyIsNull() {
        when(this.currencyRepository.findAllCurrenciesExchangeRates())
                .thenReturn(this.currenciesFromRepository);

        Double expectedValue = 0.0;

        Double result = this.exchangeRateFinderService.findExchangeRate(null);

        assertThat(result).isEqualTo(expectedValue);
    }
}