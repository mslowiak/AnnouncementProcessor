package extractor.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
public class Price {

    private BigDecimal basePrice;
    private String currency = "PLN";
    private Map<String, BigDecimal> additionalPrices;
}
