package extractor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/*
* Contains extracted data which is sourced by the parser service.
* Groups the data into categories
*
* */
@AllArgsConstructor
@Data
public class Announcement {

    private String title;
    private Price price;
    private Location location;
    private PropertyData propertyData;
    private Lessor lessor;
    private LocalDate creationDate;
    private String url;
    private String description;
    private String provider; // to enum
}
