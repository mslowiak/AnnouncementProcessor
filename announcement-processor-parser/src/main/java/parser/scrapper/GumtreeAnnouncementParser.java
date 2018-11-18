package parser.scrapper;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.cglib.core.Local;
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
        return "";
    }

    @Override
    public BigDecimal parsePrice(Element priceElement) {
        return BigDecimal.ZERO;
    }

    @Override
    public LocalDateTime parseCreationDate(Element creationDateElement) {
        return LocalDateTime.MIN;
    }

    @Override
    public String parseDescription(Element element) {
        return "";
    }

    @Override
    public String parseLessor(Element lessorElement) {
        return "";
    }

    @Override
    public String parserLessorName(Element lessorNameElement) {
        return "";
    }

    @Override
    public String parsePhoneNumber(Element phoneNumberElement) {
        return "";
    }

    @Override
    public String parsePropertyType(Element propertyTypeElement) {
        return "";
    }

    @Override
    public Double parseFlatArea(Element flatAreaElement) {
        return 0d;
    }

    @Override
    public Integer parseRoomAmount(Element roomAmountElement) {
        return -1;
    }

    @Override
    public Integer parseBathAmount(Element bathAmountElement) {
        return -1;
    }

    @Override
    public String parseParking(Element parkingElement) {
        return "";
    }

    @Override
    public Boolean parserSmokers(Element smokersElement) {
        return false;
    }

    @Override
    public Boolean parserPetFriendly(Element petFriendlyElement) {
        return true;
    }

    @Override
    public BigDecimal parseAdditionalRentCost(Element additionalRentCostElement) {
        return BigDecimal.ZERO;
    }

    @Override
    public String parseLevel(Element levelElement) {
        return "";
    }

    @Override
    public String parseFurnishings(Element furnishingsElement) {
        return "";
    }
}
