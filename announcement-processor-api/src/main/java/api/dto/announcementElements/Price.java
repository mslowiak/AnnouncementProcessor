package api.dto.announcementElements;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
public class Price {

    private BigDecimal basePrice;
    private String currency = "PLN";
    private Map<String, BigDecimal> additionalPrices;
}
