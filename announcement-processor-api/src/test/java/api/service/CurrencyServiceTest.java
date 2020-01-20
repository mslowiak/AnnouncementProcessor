package api.service;

import api.entity.Currency;
import api.repository.CurrencyRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

public class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void calculateToCurrencyTest() {
        Optional<Currency> currencyOptional = Optional.of(new Currency(123, "EUR", "PLN", BigDecimal.valueOf(4)));
        Mockito.when(currencyRepository.findCurrencyByCurrencyFromAndCurrencyTo("EUR", "PLN"))
                .thenReturn(currencyOptional);
        CurrencyService currencyService = new CurrencyService(currencyRepository);

        BigDecimal newCurrencyValue = currencyService.calculateToCurrency("EUR", "PLN", BigDecimal.valueOf(100));

        Assert.assertEquals(BigDecimal.valueOf(400), newCurrencyValue);
    }
}
