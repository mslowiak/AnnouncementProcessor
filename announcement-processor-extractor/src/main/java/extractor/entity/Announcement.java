package extractor.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
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
