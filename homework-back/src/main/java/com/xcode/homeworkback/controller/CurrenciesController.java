package com.xcode.homeworkback.controller;

import com.xcode.homeworkback.domain.ExchangeRateFinderService;
import com.xcode.homeworkback.domain.dto.CurrencyDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currencies")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class CurrenciesController {

    private ExchangeRateFinderService exchangeRateFinderService;

    @PostMapping("/get-current-currency-value-command")
    public ResponseEntity getCurrentExchangeRate(@RequestBody CurrencyDto currency) {
        Double exchange = this.exchangeRateFinderService.findExchangeRate(currency.getCurrency());

        if (exchange == 0.0) {
            return new ResponseEntity<>("Wrong currency.", HttpStatus.BAD_REQUEST);
        }

        CurrencyDto result = new CurrencyDto(null, exchange);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
