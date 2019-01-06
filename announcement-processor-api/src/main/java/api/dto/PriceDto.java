package api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
public class PriceDto {

    private BigDecimal basePrice;
    private String currency;
    private Map<String, BigDecimal> additionalPrices;
}
