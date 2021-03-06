package api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationDto {


    private String country;
    private String city;
    private String street;
    private String zipCode;
    private String buildingNumber;
    private String flatNumber;
    private String district;
}
