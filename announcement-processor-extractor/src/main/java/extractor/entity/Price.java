package extractor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

/*
 * Consists all the prices extracted from the announcement.
 * It tries to evaluate an overall cost basing on the data contained in all the fields of the announcement.
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    // Integers may be changed to a custom Money type
    private BigDecimal basePrice;
    private String currency = "PLN";
    private Map<String, BigDecimal> additionalPrices;

//    public BigDecimal getSummedPrice() {
//        return basePrice.add(
//                additionalPrices.values()
//                        .stream()
//                        .reduce(BigDecimal.ZERO, BigDecimal::add)
//        );
//    }
}
