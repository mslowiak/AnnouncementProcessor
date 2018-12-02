package extractor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertyData {

    String propertyType; // to enum?
    Float area;
    Boolean isSmokingAllowed;
    Boolean isPerFriendly;
    Integer roomNumber;
    Integer bathroomNumber;
    String parkingAvailability;
    Integer level;
    String furnishing;

}
