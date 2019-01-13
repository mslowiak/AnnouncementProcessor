package extractor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AnnouncementDto {

    private String title;
    private BigDecimal price;
    private LocalDateTime creationDate;
    private String lessor;
    private String cityLocation;
    private String propertyType;
    private Double flatArea;
    private Integer roomAmount;
    private Integer bathAmount;
    private String parkingAvailability;
    private Boolean isSmokingAllowed;
    private Boolean isPetFriendly;
    private String lessorName;
    private BigDecimal additionalRentCost;
    private Integer level;
    private String furnishing;
    private String description;
    private String provider;
    private String url;
    private String phoneNumber;

    public AnnouncementDto(
            @JsonProperty("title") String title,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("creationDate") LocalDateTime creationDate,
            @JsonProperty("lessor") String lessor,
            @JsonProperty("cityLocation") String cityLocation,
            @JsonProperty("propertyType") String propertyType,
            @JsonProperty("flatArea") Double flatArea,
            @JsonProperty("roomAmount") Integer roomAmount,
            @JsonProperty("bathAmount") Integer bathAmount,
            @JsonProperty("parkingAvailability") String parkingAvailability,
            @JsonProperty("isSmokingAllowed") Boolean isSmokingAllowed,
            @JsonProperty("isPetFriendly") Boolean isPetFriendly,
            @JsonProperty("lessorName") String lessorName,
            @JsonProperty("additionalRentCost") BigDecimal additionalRentCost,
            @JsonProperty("level") Integer level,
            @JsonProperty("furnishing") String furnishing,
            @JsonProperty("description") String description,
            @JsonProperty("provider") String provider,
            @JsonProperty("url") String url,
            @JsonProperty("phoneNumber") String phoneNumber) {
        this.title = title;
        this.price = price;
        this.creationDate = creationDate;
        this.lessor = lessor;
        this.cityLocation = cityLocation;
        this.propertyType = propertyType;
        this.flatArea = flatArea;
        this.roomAmount = roomAmount;
        this.bathAmount = bathAmount;
        this.parkingAvailability = parkingAvailability;
        this.isSmokingAllowed = isSmokingAllowed;
        this.isPetFriendly = isPetFriendly;
        this.lessorName = lessorName;
        this.additionalRentCost = additionalRentCost;
        this.level = level;
        this.furnishing = furnishing;
        this.description = description;
        this.provider = provider;
        this.url = url;
        this.phoneNumber = phoneNumber;
    }
}
