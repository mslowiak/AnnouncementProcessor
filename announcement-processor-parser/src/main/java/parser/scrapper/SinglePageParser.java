package parser.scrapper;

import org.jsoup.nodes.Element;

import java.math.BigDecimal;
import java.time.LocalDateTime;

interface SinglePageParser {
    String parseTitle(Element titleElement);
    BigDecimal parsePrice(Element priceElement);
    LocalDateTime parseCreationDate(Element creationDateElement);
    String parseDescription(Element descriptionElement);

    String parseFlatCityLocation(Element locationElement);

    default String parseLessor(Element lessorElement) {
        return null;
    }

    default String parseLessorName(Element lessorNameElement) {
        return null;
    }

    default String parsePhoneNumber(Element phoneNumberElement) {
        return null;
    }

    default String parsePropertyType(Element propertyTypeElement) {
        return null;
    }

    default Double parseFlatArea(Element flatAreaElement){
        return null;
    }

    default Integer parseRoomAmount(Element roomAmountElement){
        return null;
    }

    default Integer parseBathAmount(Element bathAmountElement){
        return null;
    }

    default String parseParking(Element parkingElement){
        return null;
    }

    default Boolean parseSmokers(Element smokersElement){
        return null;
    }

    default Boolean parsePetFriendly(Element petFriendlyElement){
        return null;
    }

    default BigDecimal parseAdditionalRentCost(Element additionalRentCostElement){
        return null;
    }

    default String parseLevel(Element levelElement){
        return null;
    }

    default String parseFurnishings(Element furnishingsElement){
        return null;
    }
}
