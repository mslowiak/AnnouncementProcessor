package extractor.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location {

    private String country;
    private String city;
    private String street;
    private String zipCode;
    private String buildingNumber;
    private String flatNumber;
    private String district;
}
