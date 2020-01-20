package extractor.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropertyData {

    private String propertyType; // todo to enum
    private Double area;
    private Boolean isSmokingAllowed;
    private Boolean isPerFriendly;
    private Integer roomNumber;
    private Integer bathroomNumber;
    private String parkingAvailability;
    private Integer level;
    private String furnishing;
}
