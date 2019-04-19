package api.entity.price;

import api.entity.PriceOffer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue(value = "contact")
public class ContactPrice extends PriceOffer {
    public ContactPrice() {
        super();
    }

    public ContactPrice(String name) {
        super(null, "Proszę o kontakt");
    }
}
