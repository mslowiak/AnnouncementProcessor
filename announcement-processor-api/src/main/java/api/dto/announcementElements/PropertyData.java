package api.dto.announcementElements;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertyData {

    private String propertyType; // to enum?
    private Double area;
    private Boolean isSmokingAllowed;
    private Boolean isPerFriendly;
    private Integer roomNumber;
    private Integer bathroomNumber;
    private String parkingAvailability;
    private Integer level;
    private String furnishing;
}
