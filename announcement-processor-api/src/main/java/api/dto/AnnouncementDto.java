package api.dto;

import api.dto.announcementElements.Lessor;
import api.dto.announcementElements.Location;
import api.dto.announcementElements.Price;
import api.dto.announcementElements.PropertyData;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class AnnouncementDto {

    public AnnouncementDto(
            @JsonProperty("title") String title,
            @JsonProperty("price") Price price,
            @JsonProperty("location") Location location,
            @JsonProperty("propertyData") PropertyData propertyData,
            @JsonProperty("lessor") Lessor lessor,
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
    private Price price;
    private Location location;
    private PropertyData propertyData;
    private Lessor lessor;
    private LocalDateTime creationDate;
    private String url;
    private String description;
    private String provider;
}
