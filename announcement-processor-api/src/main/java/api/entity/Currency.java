package api.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "CURRENCIES")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CURRENCY_FROM")
    private String currencyFrom;

    @Column(name = "CURRENCY_TO")
    private String currencyTo;

    @Column(name = "MULTIPLIER")
    private BigDecimal multiplier;
}
