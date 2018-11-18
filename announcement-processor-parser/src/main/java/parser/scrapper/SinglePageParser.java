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

    default String parseLessor() {
        return null;
    }

    default String parserLessorName() {
        return null;
    }

    default String parsePhoneNumber() {
        return null;
    }

    default String parsePropertyType() {
        return null;
    }

    default Double parseFlatArea(){
        return null;
    }

    default Integer parseRoomAmount(){
        return null;
    }

    default Integer parseBathAmount(){
        return null;
    }

    default Boolean parseParking(){
        return null;
    }

    default Boolean parserSmokers(){
        return null;
    }

    default Boolean parserPetFriendly(){
        return null;
    }

    default BigDecimal parseAdditionalRentCost(){
        return null;
    }

    default String parseLevel(){
        return null;
    }

    default String parseFurnishings(){
        return null;
    }
}
