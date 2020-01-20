package api.model;

import api.entity.AdditionalCosts;
import api.entity.Lessor;
import api.entity.Location;
import api.entity.PropertyData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedAnnouncementInfo {
    private String title;
    private String images;
    private String baseCost;
    private String provider;
    private String creationDate;
    private String url;
    private String description;

    private PropertyData propertyData;
    private Lessor lessor;
    private List<AdditionalCosts> additionalCosts;
    private Location location;
}
