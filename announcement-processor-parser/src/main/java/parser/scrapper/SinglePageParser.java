package parser.scrapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

interface SinglePageParser {
    String parseTitle();
    BigDecimal parsePrice();
    LocalDateTime parseCreationDate();
    String parseDescription();
    String parseProvider();
    String parseUrl();

    String parseLessor();
    String parserLessorName();
    String parsePhoneNumber();

    String parsePropertyType();
    Double parseFlatArea();
    Integer parseRoomAmount();
    Integer parseBathAmount();
    Boolean parseParking();
    Boolean parserSmokers();
    Boolean parserPetFriendly();
    BigDecimal parseAdditionalRentCost();
    String parseLevel();
    String parseFurnishings();
}
