package api.model;

import api.service.CurrencyService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class Money {
    @JsonIgnore
    private CurrencyService currencyService;
    private BigDecimal price;
    private String actualCurrency;

    public Money(CurrencyService currencyService, BigDecimal price, String actualCurrency) {
        this.currencyService = currencyService;
        this.price = price;
        this.actualCurrency = actualCurrency;
    }

    public void recalculateToCurrency(String desiredCurrency) {
        BigDecimal newPrice = currencyService.calculateToCurrency(actualCurrency, desiredCurrency, price);
        if (newPrice != null) {
            actualCurrency = desiredCurrency;
            price = newPrice;
        }
    }

    public String getPriceWithCurrency() {
        return price + " " + actualCurrency;
    }
}
