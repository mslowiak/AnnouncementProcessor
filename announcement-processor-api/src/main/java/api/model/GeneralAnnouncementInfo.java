package api.model;

import lombok.Data;

@Data
public class GeneralAnnouncementInfo {
    private String title;
    private String baseCost;
    private String provider;
    private String creationDate;
    private String url;
    private String lessorType;
    private String location;
}
