package api.service;

import api.entity.Currency;
import api.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CurrencyService {
    private CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public BigDecimal calculateToCurrency(String actualCurrency, String desiredCurrency, BigDecimal price) {
        Optional<Currency> currency = currencyRepository.findCurrencyByCurrencyFromAndCurrencyTo(actualCurrency, desiredCurrency);
        return currency.map(currency1 -> price.multiply(currency1.getMultiplier())).orElse(null);
    }
}
