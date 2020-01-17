package extractor.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* Consists information about the offered propoerty location.
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
