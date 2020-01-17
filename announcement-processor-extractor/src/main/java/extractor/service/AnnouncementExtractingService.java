package extractor.service;

import extractor.dto.AnnouncementDto;
import extractor.entity.Announcement;
import extractor.entity.Lessor;
import extractor.entity.Location;
import extractor.entity.Price;
import extractor.entity.PropertyData;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AnnouncementExtractingService {

    public Announcement extractFromAnnouncementDto(AnnouncementDto announcementDto) {

        return Announcement.builder() // todo check if it's fine or if some kind of automatic converter/serializer would be better
                .title(announcementDto.getTitle())
                .images(announcementDto.getImages())
                .creationDate(announcementDto.getCreationDate())
                .description(announcementDto.getDescription())
                .provider(announcementDto.getProvider())
                .url(announcementDto.getUrl())
                .lessor(extractLessor(announcementDto))
                .location(extractLocation(announcementDto))
                .price(extractPrice(announcementDto))
                .propertyData(extractPropertyData(announcementDto))
                .build();
    }

    private Lessor extractLessor(AnnouncementDto announcementDto) {

        return Lessor.builder()
                .name(announcementDto.getLessorName())
                .lessorType(announcementDto.getLessor())
                .phoneNumber(announcementDto.getPhoneNumber())
                .build();
    }

    private Location extractLocation(AnnouncementDto announcementDto) {

        return Location.builder().build();
    }

    private Price extractPrice(AnnouncementDto announcementDto) {

        Map<String, BigDecimal> pricesMap = new HashMap<>();
        parseDescriptionPrice(announcementDto.getDescription(), pricesMap);

        return Price.builder()
                .basePrice(announcementDto.getPrice())
                .additionalPrices(pricesMap)
                .build();
    }

    private PropertyData extractPropertyData(AnnouncementDto announcementDto) {

        return PropertyData.builder()
                .area(announcementDto.getFlatArea())
                .bathroomNumber(announcementDto.getBathAmount())
                .furnishing(announcementDto.getFurnishing())
                .isPerFriendly(announcementDto.getIsPetFriendly())
                .isSmokingAllowed(announcementDto.getIsSmokingAllowed())
                .level(announcementDto.getLevel())
                .parkingAvailability(announcementDto.getParkingAvailability())
                .propertyType(announcementDto.getPropertyType())
                .roomNumber(announcementDto.getRoomAmount())
                .build();
    }

    private void parseDescriptionPrice(String description, Map<String, BigDecimal> pricesMap) { // todo return price map don't modify one

        ArrayList<String> utilities = new ArrayList<>(); // todo try to injecting and names not as strings but from config enums etc.
        // todo enum that later is collapsed to a list (like parsers in work), then proper price parser is associated for each item, if there's no such, a default one is used
        utilities.add("gaz");
        utilities.add("prąd");
        utilities.add("czynsz");
        utilities.add("ogrzewanie");
        utilities.add("CO");
        utilities.add("śmieci");
        utilities.add("media");
        utilities.add("internet");
        utilities.add("woda");

        for (String word : utilities) { // todo extract this algorithm to maybe interfaced methods
            Pattern p = Pattern.compile("\\b" + word);
            Matcher m = p.matcher(description);
            while (m.find()) {
                BigDecimal foundCost = checkNeighbor(description, m.end());
                if (!pricesMap.containsKey(word) || pricesMap.get(word) == null) {
                    pricesMap.put(word, foundCost);
                }
            }
        }

    }

    private BigDecimal checkNeighbor(String description, int end) {

        int i = end + 1;
        int counter = 25;
        boolean notFound = true;
        StringBuilder digitBuilder = new StringBuilder("");
        while (notFound && i <= description.length() - 1 && counter >= 0) {
            char ch = description.charAt(i);
            if (Character.isDigit(ch)) {
                digitBuilder.append(ch);
            } else {
                counter--;
                if (digitBuilder.length() > 0) {
                    return new BigDecimal(digitBuilder.toString());
                }
            }
            i++;
        }
        return null;
    }
}
