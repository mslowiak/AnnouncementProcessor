package parser.scrapper;

import lombok.extern.slf4j.Slf4j;
import parser.Announcement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
public class GumtreeAnnouncementParser extends AnnouncementParser implements SinglePageParser {
    @Override
    public Announcement parsePage(String url) {
        return null;
    }

    @Override
    public String parseTitle() {
        return null;
    }

    @Override
    public BigDecimal parsePrice() {
        return null;
    }

    @Override
    public LocalDateTime parseCreationDate() {
        return null;
    }

    @Override
    public String parseDescription() {
        return null;
    }

    @Override
    public String parseProvider() {
        return null;
    }

    @Override
    public String parseUrl() {
        return null;
    }

    @Override
    public String parseLessor() {
        return null;
    }

    @Override
    public String parserLessorName() {
        return null;
    }

    @Override
    public String parsePhoneNumber() {
        return null;
    }

    @Override
    public String parsePropertyType() {
        return null;
    }

    @Override
    public Double parseFlatArea() {
        return null;
    }

    @Override
    public Integer parseRoomAmount() {
        return null;
    }

    @Override
    public Integer parseBathAmount() {
        return null;
    }

    @Override
    public Boolean parseParking() {
        return null;
    }

    @Override
    public Boolean parserSmokers() {
        return null;
    }

    @Override
    public Boolean parserPetFriendly() {
        return null;
    }
}
