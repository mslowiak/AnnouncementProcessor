package extractor.service;

import extractor.dto.AnnouncementDto;
import extractor.entity.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AnnouncementExtractingService {

    public Announcement extractFromAnnouncementDto(AnnouncementDto announcementDto) {

        Announcement announcement = new Announcement();

        announcement.setTitle(announcementDto.getTitle());
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
        // TODO more advanced extraction e.g. description data extraction
        return lessor;
    }

    private Location extractLocation(AnnouncementDto announcementDto) {
        Location location = new Location();
        // TODO more advanced extraction e.g. description data extraction
        return location;
    }

    private Price extractPrice(AnnouncementDto announcementDto) {
        Price price = new Price();
        price.setBasePrice(announcementDto.getPrice());
        // TODO more advanced extraction e.g. description data extraction
        Map<String, Integer> pricesMap = new HashMap<>();
        parseDescriptionPrice(announcementDto.getDescription(), pricesMap);
        pricesMap.put("SomePrice", announcementDto.getAdditionalRentCost());
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
        // TODO more advanced extraction e.g. description data extraction
        return propertyData;
    }

    private void parseDescriptionPrice(String description, Map<String, Integer> pricesMap) {

    }
}
