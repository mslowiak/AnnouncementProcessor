package parser.scrapper;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import parser.Announcement;
import parser.exceptions.GumtreePageParseException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
public class GumtreeAnnouncementParser extends AnnouncementParser implements SinglePageParser {
    @Override
    public Announcement parsePage(String url) {
        try {
            Document pageContent = getPageContent(url);
            log.info("Received content from url: " + url);

            return null;
        } catch (IOException e) {
            throw new GumtreePageParseException("Nie można przetworzyć ogłoszenia o podanym url: " + url);
        }
    }

    @Override
    public String parseTitle(Element titleElement) {
        return null;
    }

    @Override
    public BigDecimal parsePrice(Element priceElement) {
        return null;
    }

    @Override
    public LocalDateTime parseCreationDate(Element creationDateElement) {
        return null;
    }

    @Override
    public String parseDescription(Element element) {
        return null;
    }

    @Override
    public String parseLessor(Element lessorElement) {
        return null;
    }

    @Override
    public String parserLessorName(Element lessorNameElement) {
        return null;
    }

    @Override
    public String parsePhoneNumber(Element phoneNumberElement) {
        return null;
    }

    @Override
    public String parsePropertyType(Element propertyTypeElement) {
        return null;
    }

    @Override
    public Double parseFlatArea(Element flatAreaElement) {
        return null;
    }

    @Override
    public Integer parseRoomAmount(Element roomAmountElement) {
        return null;
    }

    @Override
    public Integer parseBathAmount(Element bathAmountElement) {
        return null;
    }

    @Override
    public String parseParking(Element parkingElement) {
        return null;
    }

    @Override
    public Boolean parserSmokers(Element smokersElement) {
        return null;
    }

    @Override
    public Boolean parserPetFriendly(Element petFriendlyElement) {
        return null;
    }

    @Override
    public BigDecimal parseAdditionalRentCost(Element additionalRentCostElement) {
        return null;
    }

    @Override
    public String parseLevel(Element levelElement) {
        return null;
    }

    @Override
    public String parseFurnishings(Element furnishingsElement) {
        return null;
    }
}
