package api.converter;

import api.dto.AnnouncementDto;
import api.entity.*;
import api.entity.price.ConsumerPrice;
import api.entity.price.ContactPrice;
import api.entity.price.ExchangePrice;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnnouncementDtoEntityConverter implements Converter<AnnouncementDto, Announcement> {

    @Override
    public Announcement convert(MappingContext<AnnouncementDto, Announcement> mappingContext) {

        AnnouncementDto announcementDto = mappingContext.getSource();

        Announcement announcement = new Announcement();
        announcement.setTitle(announcementDto.getTitle());
        announcement.setProvider(announcementDto.getProvider());
        announcement.setCreationDate(announcementDto.getCreationDate());
        announcement.setUrl(announcementDto.getUrl());
        announcement.setDescription(announcementDto.getDescription());
        announcement.setCurrency(announcementDto.getPrice().getCurrency());

        if (announcementDto.getPrice().getBasePrice() == null) {
            ContactPrice contactPrice = new ContactPrice("contact");
            announcement.setPriceOffer(contactPrice);
        } else if (announcementDto.getPrice().getBasePrice().equals(BigDecimal.valueOf(-1))) {
            ExchangePrice exchangePrice = new ExchangePrice("exchange");
            announcement.setPriceOffer(exchangePrice);
        } else {
            ConsumerPrice consumerPrice = new ConsumerPrice("name", announcementDto.getPrice().getBasePrice());
            announcement.setPriceOffer(consumerPrice);
        }

        Location location = new Location();
        location.setAnnouncement(announcement);
        location.setCountry(announcementDto.getLocation().getCountry());
        location.setCity(announcementDto.getLocation().getCity());
        location.setStreet(announcementDto.getLocation().getStreet());
        location.setZipCode(announcementDto.getLocation().getZipCode());
        location.setBuildingNumber(announcementDto.getLocation().getBuildingNumber());
        location.setFlatNumber(announcementDto.getLocation().getFlatNumber());
        location.setDistrict(announcementDto.getLocation().getDistrict());
        announcement.setLocation(location);

        PropertyData propertyData = new PropertyData();
        propertyData.setAnnouncement(announcement);
        propertyData.setPropertyType(announcementDto.getPropertyData().getPropertyType());
        propertyData.setArea(announcementDto.getPropertyData().getArea());
        propertyData.setIsSmokingAllowed(announcementDto.getPropertyData().getIsSmokingAllowed());
        propertyData.setIsPetFriendly(announcementDto.getPropertyData().getIsPerFriendly());
        propertyData.setRoomNumber(announcementDto.getPropertyData().getRoomNumber());
        propertyData.setBathroomNumber(announcementDto.getPropertyData().getBathroomNumber());
        propertyData.setParkingAvailability(announcementDto.getPropertyData().getParkingAvailability());
        propertyData.setLevel(announcementDto.getPropertyData().getLevel());
        propertyData.setFurnishing(announcementDto.getPropertyData().getFurnishing());
        announcement.setPropertyData(propertyData);

        Lessor lessor = new Lessor();
        lessor.setAnnouncement(announcement);
        lessor.setName(announcementDto.getLessor().getName());
        lessor.setLessorType(announcementDto.getLessor().getLessorType());
        lessor.setPhoneNumber(announcementDto.getLessor().getPhoneNumber());
        lessor.setEmail(announcementDto.getLessor().getEmail());
        announcement.setLessor(lessor);

        List<AdditionalCosts> additionalCostsList = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> entry : announcementDto.getPrice().getAdditionalPrices().entrySet()) {
            AdditionalCosts additionalCosts = new AdditionalCosts();
            additionalCosts.setAnnouncement(announcement);
            additionalCosts.setCostName(entry.getKey());
            additionalCosts.setCostPrice(entry.getValue());
            additionalCostsList.add(additionalCosts);
        }
        announcement.setAdditionalCosts(additionalCostsList);

        return announcement;
    }
}
