package api.entity.price;

import api.entity.PriceOffer;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue(value = "exchange")
public class ExchangePrice extends PriceOffer {
    public ExchangePrice() {
    }

    public ExchangePrice(String name) {
        super("Wymiana/Zamiana");
    }
}
