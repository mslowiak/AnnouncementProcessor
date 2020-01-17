package extractor.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
* Contains extracted data which is sourced by the parser service.
* Groups the data into categories
*
* */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder // todo check if annotations with constructors are needed1!!
public class Announcement {

    private String title;
    private String images;
    private Price price;
    private Location location;
    private PropertyData propertyData;
    private Lessor lessor;
    private LocalDateTime creationDate;
    private String url;
    private String description;
    private String provider;
}
