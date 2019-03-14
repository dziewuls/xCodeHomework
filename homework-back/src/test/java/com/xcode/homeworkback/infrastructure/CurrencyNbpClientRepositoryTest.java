package com.xcode.homeworkback.infrastructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xcode.homeworkback.domain.model.Currency;
import com.xcode.homeworkback.domain.port.CurrencyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyNbpClientRepositoryTest {

    @Mock
    private RestTemplate restTemplate;

    private CurrencyRepository currencyRepository;

    @Before
    public void initialCurrencyRepository() {
        this.currencyRepository = new CurrencyNbpClientRepository(this.restTemplate);
    }

    @Test
    public void shouldReturnExpectedCurrenciesList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String exampleCurrencies = "[{\"table\":\"A\",\"no\":\"051/A/NBP/2019\",\"effectiveDate\":\"2019-03-13\",\"rates\":[{\"currency\":\"bat (Tajlandia)\",\"code\":\"THB\",\"mid\":0.1204},{\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"mid\":3.8077},{\"currency\":\"SDR (MFW)\",\"code\":\"XDR\",\"mid\":5.2952}]}]";
        JsonNode exampleJsonNode = objectMapper.readTree(exampleCurrencies);
        ResponseEntity<JsonNode> responseFromRepository = new ResponseEntity<>(exampleJsonNode, HttpStatus.OK);

        when(this.restTemplate.getForEntity(anyString(), eq(JsonNode.class))).thenReturn(responseFromRepository);

        List<Currency> expectedCurrencies = new ArrayList<>(Arrays.asList(
                new Currency("THB", 0.1204),
                new Currency("USD", 3.8077),
                new Currency("XDR", 5.2952)
        ));

        List<Currency> result = this.currencyRepository.findAllCurrenciesExchangeRates();

        assertThat(result).containsExactlyInAnyOrderElementsOf(expectedCurrencies);
    }

    @Test
    public void shouldReturnEmptyListWhenResponseHasNoBody() {
        ResponseEntity<JsonNode> responseFromRepository = new ResponseEntity<>(HttpStatus.OK);
        List<Currency> expectedCurrencies = Collections.emptyList();

        when(this.restTemplate.getForEntity(anyString(), eq(JsonNode.class))).thenReturn(responseFromRepository);

        List<Currency> result = this.currencyRepository.findAllCurrenciesExchangeRates();

        assertThat(result).containsExactlyInAnyOrderElementsOf(expectedCurrencies);
    }
}