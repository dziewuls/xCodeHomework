package com.xcode.homeworkback.controller;

import com.xcode.homeworkback.domain.ExchangeRateFinderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrenciesController.class)
public class CurrenciesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExchangeRateFinderService exchangeRateFinderService;

    @Test
    public void shouldReturnHttpStatusOkAndExpectedExchangeRate() throws Exception {
        String givenCurrency = "EUR";
        Double expectedExchange = 4.2954;

        when(this.exchangeRateFinderService.findExchangeRate(givenCurrency)).thenReturn(expectedExchange);

        mockMvc.perform(post("/currencies/get-current-currency-value-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currency\":\"EUR\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"value\":4.2954}"));
    }

    @Test
    public void shouldReturnHttpStatusBadRequestWhenGivenCurrencyNotExist() throws Exception {
        String givenCurrency = "some";
        Double expectedExchange = 0.0;

        when(this.exchangeRateFinderService.findExchangeRate(givenCurrency)).thenReturn(expectedExchange);

        mockMvc.perform(post("/currencies/get-current-currency-value-command")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currency\":\"some\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Wrong currency."));
    }

    @Test
    public void shouldReturnHttpStatusBadRequestWhenPostEmptyBody() throws Exception {
        mockMvc.perform(post("/currencies/get-current-currency-value-command"))
                .andExpect(status().isBadRequest());
    }
}