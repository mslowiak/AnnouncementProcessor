package api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
