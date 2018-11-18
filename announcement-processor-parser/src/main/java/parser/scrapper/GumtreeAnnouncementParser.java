package parser.scrapper;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.Announcement;
import parser.exceptions.GumtreePageParseException;
import parser.exceptions.PropertyNotValidForGumtreeProviderException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class GumtreeAnnouncementParser extends AnnouncementParser implements SinglePageParser {
    @Override
    public Announcement parsePage(String url) {
        try {
            Document pageContent = getPageContent(url);
            log.info("Received content from url: " + url);

            Element details = pageContent.selectFirst(".vip-details");

            return Announcement.builder()
                    .title(parseTitle(pageContent.selectFirst(".item-title")))
                    .price(parsePrice(pageContent.selectFirst(".price")))
                    .lessorName(parseLessorName(pageContent.selectFirst(".username")))
                    .creationDate(parseCreationDate(details))
//                    .description(parseDescription(pageContent.selectFirst(".description")))
                    .lessor(parseLessor(details))
                    .phoneNumber(parsePhoneNumber(pageContent.selectFirst(".vip.vip-contact")))
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
    public String parseLessorName(Element lessorNameElement) {
        Element href = lessorNameElement.selectFirst("a");
        String text = href.text();
        return text.replaceAll("\\(Zobacz więcej ogłoszeń\\)", "").trim();
    }

    @Override
    public LocalDateTime parseCreationDate(Element creationDateElement) {
        LocalDateTime output = null;

        Elements liElements = creationDateElement.select("li");
        String dateInText = getValueForAttributeFromLiElements("Data dodania", liElements);

        if (dateInText != null) {
            output = LocalDateTime.of(LocalDate.parse(dateInText, DateTimeFormatter.ofPattern("d/MM/yyyy")), LocalTime.of(0, 0));
        }

        return output;
    }

    @Override
    public String parseDescription(Element element) {
        return "";
    }

    @Override
    public String parseLessor(Element lessorElement) {
        Elements liElements = lessorElement.select("li");
        return getValueForAttributeFromLiElements("Do wynajęcia przez", liElements);
    }

    @Override
    public String parsePhoneNumber(Element phoneNumberElement) {
        Element element = phoneNumberElement.selectFirst("div > a");
        String telephone = element.attr("href");
        if(telephone.contains("tel:")){
            return telephone.replaceAll("tel:", "");
        }
        return null;
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
    public Boolean parseSmokers(Element smokersElement) {
        return false;
    }

    @Override
    public Boolean parsePetFriendly(Element petFriendlyElement) {
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

    private String getValueForAttributeFromLiElements(String attributeName, Elements liElements) {
        if (liElements != null) {
            for (Element singleLiElement : liElements) {
                String text = singleLiElement.selectFirst(".attribute > .name").text();
                if (text.equals(attributeName)) {
                    return singleLiElement.selectFirst(".attribute > .value").text();
                }
            }
        }
        return null;
    }
}
