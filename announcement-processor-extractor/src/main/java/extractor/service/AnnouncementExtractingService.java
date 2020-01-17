package extractor.service;

import extractor.dto.AnnouncementDto;
import extractor.entity.*;
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

        Announcement announcement = new Announcement();

        announcement.setTitle(announcementDto.getTitle());
        announcement.setImages(announcementDto.getImages());
        announcement.setCreationDate(announcementDto.getCreationDate());
        announcement.setDescription(announcementDto.getDescription());
        announcement.setProvider(announcementDto.getProvider());
        announcement.setUrl(announcementDto.getUrl());

        announcement.setLessor(extractLessor(announcementDto));
        announcement.setLocation(extractLocation(announcementDto));
        announcement.setPrice(extractPrice(announcementDto));
        announcement.setPropertyData(extractPropertyData(announcementDto));

        return announcement;
    }

    private Lessor extractLessor(AnnouncementDto announcementDto) {
        Lessor lessor = new Lessor();
        lessor.setName(announcementDto.getLessorName());
        lessor.setLessorType(announcementDto.getLessor());
        lessor.setPhoneNumber(announcementDto.getPhoneNumber());
        return lessor;
    }

    private Location extractLocation(AnnouncementDto announcementDto) {
        Location location = new Location();

        return location;
    }

    private Price extractPrice(AnnouncementDto announcementDto) {
        Price price = new Price();
        price.setBasePrice(announcementDto.getPrice());
        Map<String, BigDecimal> pricesMap = new HashMap<>();

        parseDescriptionPrice(announcementDto.getDescription(), pricesMap);

        price.setAdditionalPrices(pricesMap);
        return price;
    }

    private PropertyData extractPropertyData(AnnouncementDto announcementDto) {
        PropertyData propertyData = new PropertyData();
        propertyData.setArea(announcementDto.getFlatArea());
        propertyData.setBathroomNumber(announcementDto.getBathAmount());
        propertyData.setFurnishing(announcementDto.getFurnishing());
        propertyData.setIsPerFriendly(announcementDto.getIsPetFriendly());
        propertyData.setIsSmokingAllowed(announcementDto.getIsSmokingAllowed());
        propertyData.setLevel(announcementDto.getLevel());
        propertyData.setParkingAvailability(announcementDto.getParkingAvailability());
        propertyData.setPropertyType(announcementDto.getPropertyType());
        propertyData.setRoomNumber(announcementDto.getRoomAmount());
        return propertyData;
    }

    private void parseDescriptionPrice(String description, Map<String, BigDecimal> pricesMap) {

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
