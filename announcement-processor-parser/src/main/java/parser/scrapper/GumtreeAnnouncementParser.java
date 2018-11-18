package parser.scrapper;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import parser.Announcement;
import parser.exceptions.GumtreePageParseException;
import parser.exceptions.PropertyNotValidForGumtreeProviderException;

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

            return Announcement.builder()
                    .title(parseTitle(pageContent.selectFirst(".item-title")))
                    .price(parsePrice(pageContent.selectFirst(".price")))
                    .build();
        } catch (IOException e) {
            throw new GumtreePageParseException("Cannot parser announcement from url: " + url);
        }
    }

    @Override
    public String parseTitle(Element titleElement) {
        return titleElement.text();
    }

    @Override
    public BigDecimal parsePrice(Element priceElement) {
        BigDecimal output = BigDecimal.ZERO;
        Element span = priceElement.selectFirst("span");
        String text = span.text();

        if (text.contains("zł")) {
            String number = text.replaceAll("zł", "").replaceAll(" ", "");
            output = new BigDecimal(number);
        } else if (text.equals("Proszę o kontakt")) {
            output = null;
        } else if (text.equals("Wymiana/zamiana")) {
            output = new BigDecimal(-1);
        }

        return output;
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
        throw new PropertyNotValidForGumtreeProviderException("additional rent cost");
    }

    @Override
    public String parseLevel(Element levelElement) {
        throw new PropertyNotValidForGumtreeProviderException("level");
    }

    @Override
    public String parseFurnishings(Element furnishingsElement) {
        throw new PropertyNotValidForGumtreeProviderException("furnishings");
    }
}
