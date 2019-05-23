package api.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SearchCriteria {
    private List<String> region;
    private List<String> lessor;
    private List<String> rooms;
    private List<String> baths;
    private List<String> parking;
    private List<String> smokers;
    private List<String> pets;
    private Integer priceFrom;
    private Integer priceTo;
    private Integer areaFrom;
    private Integer areaTo;
}
