package api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;

@ToString
@Data
public class AnnouncementDto {

    public AnnouncementDto(
            @JsonProperty("title") String title,
            @JsonProperty("price") PriceDto price,
            @JsonProperty("location") LocationDto location,
            @JsonProperty("propertyData") PropertyDataDto propertyData,
            @JsonProperty("lessor") LessorDto lessor,
            @JsonProperty("creationDate") LocalDateTime creationDate,
            @JsonProperty("url") String url,
            @JsonProperty("description") String description,
            @JsonProperty("provider") String provider) {
        this.title = title;
        this.price = price;
        this.location = location;
        this.propertyData = propertyData;
        this.lessor = lessor;
        this.creationDate = creationDate;
        this.url = url;
        this.description = description;
        this.provider = provider;
    }

    private String title;
    private PriceDto price;
    private LocationDto location;
    private PropertyDataDto propertyData;
    private LessorDto lessor;
    private LocalDateTime creationDate;
    private String url;
    private String description;
    private String provider;
}
