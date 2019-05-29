package api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralAnnouncementInfo {
    private Integer id;
    private String title;
    private String images;
    private BigDecimal baseCost;
    private String description;
    private String provider;
    private LocalDateTime creationDate;
    private String url;
    private String lessorType;
    private String location;

    public GeneralAnnouncementInfo(Integer id, String title, BigDecimal baseCost, String description, String provider,
                                   LocalDateTime creationDate, String url, String lessorType, String location) {
        this.id = id;
        this.title = title;
        this.baseCost = baseCost;
        this.description = description;
        this.provider = provider;
        this.creationDate = creationDate;
        this.url = url;
        this.lessorType = lessorType;
        this.location = location;
    }
}
