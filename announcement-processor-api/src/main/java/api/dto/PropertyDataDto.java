package api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertyDataDto {

    private String propertyType;
    private Double area;
    private Boolean isSmokingAllowed;
    private Boolean isPerFriendly;
    private Integer roomNumber;
    private Integer bathroomNumber;
    private String parkingAvailability;
    private Integer level;
    private String furnishing;
}
