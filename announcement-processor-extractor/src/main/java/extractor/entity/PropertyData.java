package extractor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertyData {

    private String propertyType; // to enum?
    private Float area;
    private Boolean isSmokingAllowed;
    private Boolean isPerFriendly;
    private Integer roomNumber;
    private Integer bathroomNumber;
    private String parkingAvailability;
    private Integer level;
    private String furnishing;
}
