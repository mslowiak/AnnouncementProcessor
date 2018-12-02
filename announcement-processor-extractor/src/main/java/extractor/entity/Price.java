package extractor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/*
 * Consists all the prices extracted from the announcement.
 * It tries to evaluate an overall cost basing on the data contained in all the fields of the announcement.
 * */
@Data
@AllArgsConstructor
public class Price {

    // Integers may be changed to a custom Money type
    private Integer basePrice;
    private Map<String, Integer> additionalPrices;

    public Integer getSummedPrice() {
        return basePrice + additionalPrices.values()
                .stream()
                .mapToInt(i -> i)
                .sum();
    }
}
