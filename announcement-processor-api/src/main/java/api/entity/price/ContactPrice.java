package api.entity.price;

import api.entity.PriceOffer;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue(value = "contact")
public class ContactPrice extends PriceOffer {
    public ContactPrice() {
        super();
    }

    public ContactPrice(String name) {
        super("Proszę o kontakt");
    }
}
