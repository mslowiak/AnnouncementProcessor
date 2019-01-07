package api.entity.price;

import api.entity.PriceOffer;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Data
@DiscriminatorValue(value = "real_price")
public class ConsumerPrice extends PriceOffer {

    @Column(name = "REAL_PRICE")
    private BigDecimal price;

    public ConsumerPrice() {
        super();
    }

    public ConsumerPrice(String name, BigDecimal price) {
        super("Cena");
        this.price = price;
    }
}
