package api.entity.price;

import api.entity.PriceOffer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue(value = "exchange")
public class ExchangePrice extends PriceOffer {
    public ExchangePrice() {
    }

    public ExchangePrice(String name) {
        super(null, "Wymiana/Zamiana");
    }
}
