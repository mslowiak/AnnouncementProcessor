package api.model;

import api.entity.AdditionalCosts;
import api.entity.Lessor;
import api.entity.Location;
import api.entity.PropertyData;
import lombok.Data;

import java.util.List;

@Data
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
