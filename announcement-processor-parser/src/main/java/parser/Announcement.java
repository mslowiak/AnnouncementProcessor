package parser;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Announcement {
    private String title;
    private String images;
    private BigDecimal price;
    private LocalDateTime creationDate;
    private String lessor;
    private String propertyType;
    private Double flatArea;
    private Integer roomAmount;
    private Integer bathAmount;
    private String parkingAvailability;
    private Boolean isSmokingAllowed;
    private Boolean isPetFriendly;
    private String lessorName;
    private BigDecimal additionalRentCost;
    private String level;
    private String furnishing;
    private String description;
    private String provider;
    private String url;
    private String phoneNumber;
}
