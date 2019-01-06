package api.entity.price;

import api.entity.PriceOffer;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "exchange")
public class ExchangePrice extends PriceOffer {
    public ExchangePrice() {
    }

    public ExchangePrice(String name) {
        super("Wymiana/Zamiana");
    }
}
