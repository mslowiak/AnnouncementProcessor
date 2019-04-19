package api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DESCRIPTOR")
@Table(name = "PRICE_OFFER")
public class PriceOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "REAL_PRICE")
    private BigDecimal price;

    @Column(name = "DESCRIPTION")
    private String description;

    @JsonBackReference
    @OneToOne(mappedBy = "priceOffer")
    private Announcement announcement;

    public PriceOffer() {
    }

    public PriceOffer(BigDecimal price, String description) {
        this.price = price;
        this.description = description;
    }
}
