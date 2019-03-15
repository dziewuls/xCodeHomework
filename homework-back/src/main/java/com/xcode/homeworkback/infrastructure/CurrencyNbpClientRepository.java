package com.xcode.homeworkback.infrastructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.xcode.homeworkback.domain.model.Currency;
import com.xcode.homeworkback.domain.port.CurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class CurrencyNbpClientRepository implements CurrencyRepository {

    private RestTemplate restTemplate;

    @Override
    public List<Currency> findAllCurrenciesExchangeRates() {
        String sourceUrl = "http://api.nbp.pl/api/exchangerates/tables/A?format=json";
        ResponseEntity<JsonNode> response = this.restTemplate.getForEntity(sourceUrl, JsonNode.class);

        return response.hasBody() ?
                this.parseJsonToCurrenciesList(response.getBody()) :
                Collections.emptyList();
    }

    private List<Currency> parseJsonToCurrenciesList(JsonNode currenciesInJsonFormat) {
        List<Currency> result = new ArrayList<>();
        JsonNode rates = currenciesInJsonFormat.get(0).get("rates");

        rates.forEach(c -> {
            Currency currency = this.parseJsonToCurrency(c);
            result.add(currency);
        });

        return result;
    }

    private Currency parseJsonToCurrency(JsonNode c) {
        Currency currency = new Currency();

        if (c.has("code"))
            currency.setCurrencyCode(c.get("code").asText());

        if (c.has("mid") && c.get("mid").isDouble())
            currency.setValue(c.get("mid").asDouble());

        return currency;
    }
}
