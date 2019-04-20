package api.entity.price;

import api.entity.PriceOffer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue(value = "real_price")
public class ConsumerPrice extends PriceOffer {
    public ConsumerPrice() {
        super();
    }

    public ConsumerPrice(String name, BigDecimal price) {
        super(price, "Cena");
    }
}
