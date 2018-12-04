package parser.scrapper;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
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
@Component
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
                    .description(parseDescription(pageContent.selectFirst(".description")))
                    .lessor(parseLessor(details))
                    .phoneNumber(parsePhoneNumber(pageContent.selectFirst(".vip.vip-contact")))
                    .propertyType(parsePropertyType(details))
                    .flatArea(parseFlatArea(details))
                    .roomAmount(parseRoomAmount(details))
                    .bathAmount(parseBathAmount(details))
                    .parkingAvailability(parseParking(details))
                    .isSmokingAllowed(parseSmokers(details))
                    .isPetFriendly(parsePetFriendly(details))
                    .url(url)
                    .provider("Gumtree")
                    .build();
        } catch (IOException e) {
            throw new GumtreePageParseException("Cannot parser announcement from url: " + url);
        }
    }

    @Override
    public String parseTitle(Element titleElement) {
        String title = null;

        if (titleElement != null) {
            title = titleElement.text();
        }

        return title;
    }

    @Override
    public BigDecimal parsePrice(Element priceElement) {
        BigDecimal output = BigDecimal.ZERO;

        if (priceElement != null) {
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
        }

        return output;
    }

    @Override
    public String parseLessorName(Element lessorNameElement) {
        String lessorName = null;

        if (lessorNameElement != null) {
            Element href = lessorNameElement.selectFirst("a");
            String text = href.text();
            lessorName = text.replaceAll("\\(Zobacz więcej ogłoszeń\\)", "").trim();
        }

        return lessorName;
    }

    @Override
    public LocalDateTime parseCreationDate(Element creationDateElement) {
        LocalDateTime output = null;

        if (creationDateElement != null) {
            Elements liElements = creationDateElement.select("li");
            String dateInText = getValueForAttributeFromLiElements("Data dodania", liElements);

            if (dateInText != null) {
                output = LocalDateTime.of(LocalDate.parse(dateInText, DateTimeFormatter.ofPattern("d/MM/yyyy")), LocalTime.of(0, 0));
            }
        }

        return output;
    }

    @Override
    public String parseDescription(Element element) {
        String description = null;

        if (element != null) {
            Element span = element.selectFirst("span");
            String textWithoutBoldings = span.html().replaceAll("<b>|</b>", "");
            Document parse = Jsoup.parse(textWithoutBoldings);

            Elements allElements = parse.body().getAllElements();
            StringBuilder sb = new StringBuilder();
            for (Element elem : allElements) {
                String ownText = elem.ownText();
                if (!ownText.equals("")) {
                    sb.append(elem.ownText()).append("\n");
                } else {
                    if (elem.html().equals("<br>")) {
                        sb.append("\n");
                    }
                }
            }
            description = sb.toString().trim();
        }

        return description;
    }

    @Override
    public String parseLessor(Element lessorElement) {
        String lessor = null;

        if (lessorElement != null) {
            Elements liElements = lessorElement.select("li");
            lessor = getValueForAttributeFromLiElements("Do wynajęcia przez", liElements);
        }

        return lessor;
    }

    @Override
    public String parsePhoneNumber(Element phoneNumberElement) {
        String phoneNumber = null;

        if (phoneNumberElement != null) {
            Element element = phoneNumberElement.selectFirst("div > a");
            String telephone = element.attr("href");
            if (telephone.contains("tel:")) {
                phoneNumber = telephone.replaceAll("tel:", "");
            }
        }
        return phoneNumber;
    }

    @Override
    public String parsePropertyType(Element propertyTypeElement) {
        String propertyType = null;

        if (propertyTypeElement != null) {
            Elements liElements = propertyTypeElement.select("li");
            propertyType = getValueForAttributeFromLiElements("Rodzaj nieruchomości", liElements);
        }

        return propertyType;
    }

    @Override
    public Double parseFlatArea(Element flatAreaElement) {
        Double flatArea = null;

        if (flatAreaElement != null) {
            Elements liElements = flatAreaElement.select("li");
            String flatAreaInText = getValueForAttributeFromLiElements("Wielkość (m2)", liElements);

            if (flatAreaInText != null) {
                flatArea = Double.valueOf(flatAreaInText);
            }
        }

        return flatArea;
    }

    @Override
    public Integer parseRoomAmount(Element roomAmountElement) {
        Integer roomAmount = null;

        if (roomAmountElement != null) {
            Elements liElements = roomAmountElement.select("li");
            String roomAmountText = getValueForAttributeFromLiElements("Liczba pokoi", liElements);

            if (roomAmountText != null) {
                switch (roomAmountText) {
                    case "Kawalerka lub garsoniera":
                        roomAmount = 1;
                        break;
                    case "2 pokoje":
                        roomAmount = 2;
                        break;
                    case "3 pokoje":
                        roomAmount = 3;
                        break;
                    case "4 pokoje":
                        roomAmount = 4;
                        break;
                    case "5 pokoi":
                        roomAmount = 5;
                        break;
                    case "6 lub więcej pokoi":
                        roomAmount = 6;
                        break;
                }
            }
        }

        return roomAmount;
    }

    @Override
    public Integer parseBathAmount(Element bathAmountElement) {
        Integer bathAmount = null;

        if (bathAmountElement != null) {
            Elements liElements = bathAmountElement.select("li");
            String bathAmountText = getValueForAttributeFromLiElements("Liczba łazienek", liElements);

            if (bathAmountText != null) {
                switch (bathAmountText) {
                    case "1 łazienka":
                        bathAmount = 1;
                        break;
                    case "2 łazienki":
                        bathAmount = 2;
                        break;
                    case "3 łazienki":
                        bathAmount = 3;
                        break;
                    case "4 lub więcej łazienek":
                        bathAmount = 4;
                        break;
                }
            }
        }

        return bathAmount;
    }

    @Override
    public String parseParking(Element parkingElement) {
        String parking = null;

        if (parkingElement != null) {
            Elements liElements = parkingElement.select("li");
            parking = getValueForAttributeFromLiElements("Parking", liElements);
        }

        return parking;
    }

    @Override
    public Boolean parseSmokers(Element smokersElement) {
        Boolean isForSmokers = null;

        if (smokersElement != null) {
            Elements liElements = smokersElement.select("li");
            String smokers = getValueForAttributeFromLiElements("Palący", liElements);
            isForSmokers = getBooleanFromYesNoSentence(smokers);
        }

        return isForSmokers;
    }

    @Override
    public Boolean parsePetFriendly(Element petFriendlyElement) {
        Boolean isPetFriendly = null;

        if (petFriendlyElement != null) {
            Elements liElements = petFriendlyElement.select("li");
            String pets = getValueForAttributeFromLiElements("Przyjazne zwierzakom", liElements);
            isPetFriendly = getBooleanFromYesNoSentence(pets);
        }

        return isPetFriendly;
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

    private Boolean getBooleanFromYesNoSentence(String sentence) {
        if (sentence != null) {
            if (sentence.equals("Tak")) {
                return true;
            } else if (sentence.equals("Nie")) {
                return false;
            }
        }
        return null;
    }
}
