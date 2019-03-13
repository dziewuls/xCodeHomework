package com.xcode.homeworkback.infrastructure;

import com.xcode.homeworkback.domain.model.Currency;
import com.xcode.homeworkback.domain.port.CurrencyRepository;

import java.util.List;

public class CurrencyNbpClientRepository implements CurrencyRepository {
    @Override
    public List<Currency> findAllCurrenciesExchangeRates() {
        return null;
    }
}
