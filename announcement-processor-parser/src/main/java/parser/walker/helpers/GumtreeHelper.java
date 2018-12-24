package parser.walker.helpers;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import parser.registry.ParsingInfo;
import parser.registry.ParsingInfoService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GumtreeHelper extends ProviderHelper {

    private final String BASE_ANNOUNCEMENTS_URL = "https://www.gumtree.pl/s-mieszkania-i-domy-do-wynajecia/krakow/v1c9008l3200208p1";
    private int divElementWithAnnouncementsNumber = 1;

    public GumtreeHelper(ParsingInfoService parsingInfoService) {
        super(parsingInfoService);
    }

    @Override
    public Optional<ParsingInfo> fetchLatestRecord() {
        Optional<ParsingInfo> info = parsingInfoService.fetchLastRecordForProvider("GUMTREE", lastParsedAnnouncement);
        lastParsedAnnouncement = info.orElse(null);
        return info;
    }

    @Override
    public Optional<WalkerInfo> findPageWithAnnouncement(ParsingInfo parsingInfoToFind) {
        if (parsingInfoToFind != null) {
            try {
                String urlToScan = null;
                int requestedAnnouncementDivNumber = -1;
                Document scannedPage = null;
                int scannedPageNumber = 0;
                int totalPage = -1;
                LocalDate date;

                do {
                    scannedPageNumber++;
                    urlToScan = getNextPageUrl(urlToScan, scannedPageNumber);
                    scannedPage = getPageAsDocumentFromUrl(urlToScan);
                    date = getEarliestDateOnAnnouncementPage(scannedPage);
                    if (date != null && !date.isBefore(parsingInfoToFind.getDate())) {
                        requestedAnnouncementDivNumber = findAnnouncementDivNumberOnPage(scannedPage, parsingInfoToFind.getUrl());
                        totalPage = getNumberOfTotalPages(scannedPage);
                    }
                }
                while (requestedAnnouncementDivNumber == -1 && date != null && !date.isBefore(parsingInfoToFind.getDate()) && scannedPageNumber <= totalPage);

                if (requestedAnnouncementDivNumber != -1) {
                    walkerInfo = new WalkerInfo();
                    walkerInfo.setWalkPageUrl(urlToScan);
                    walkerInfo.setWalkPageUrlNumber(scannedPageNumber);
                    walkerInfo.setRequestedAnnouncementDivNumber(requestedAnnouncementDivNumber);
                    walkerInfo.setWalkPageDocument(scannedPage);
                }
            } catch (IOException e) {
                log.error("Error during finding page with announcement with url: " + parsingInfoToFind.getUrl());
            }
        }
        return Optional.ofNullable(walkerInfo);
    }

    @Override
    public List<String> getUrlsToParse(Document document, int divNumber) {
        log.info("Geeting urls to parse");
        return getElementsWithDataFromPage(document)
                .stream()
                .limit(divNumber)
                .map(this::getPageUrlFromElement)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllUrlsOnPage() {
        try {
            Document document = getPageAsDocumentFromUrl(walkerInfo.getWalkPageUrl());
            Elements elements = getElementsWithDataFromPage(document);

            return elements.stream().map(this::getPageUrlFromElement).collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Error in getAllUrlsOnPage");
            return null;
        }
    }

    LocalDate getEarliestDateOnAnnouncementPage(Document scannedPage) {
        Elements elementsWithData = getElementsWithDataFromPage(scannedPage);
        LocalDateTime actualDateTime = LocalDateTime.now();
        Optional<LocalDate> first = elementsWithData.stream()
                .map(element -> element.selectFirst(".creation-date").text())
                .map(date -> convertStringToLocalDate(date, actualDateTime))
                .max(Comparator.naturalOrder());
        return first.orElse(null);
    }

    LocalDate convertStringToLocalDate(String date, LocalDateTime actualDateTime) {
        LocalDate toReturn;

        if (date.contains("temu")) {
            String[] partsOfDate = date.split(" ");
            if (partsOfDate[1].equals("min")) {
                actualDateTime = actualDateTime.minusMinutes(Integer.parseInt(partsOfDate[0]));
            } else {
                actualDateTime = actualDateTime.minusHours(Integer.parseInt(partsOfDate[0]) + 1);
            }
            toReturn = actualDateTime.toLocalDate();
        } else {
            String[] dayMonth = date.split("-");
            toReturn = LocalDate.of(actualDateTime.getYear(), Integer.parseInt(dayMonth[1]), Integer.parseInt(dayMonth[0]));
        }
        return toReturn;
    }

    @Override
    Elements getElementsWithDataFromPage(Document page) {
        return page
                .select(".view").get(divElementWithAnnouncementsNumber)
                .selectFirst("ul")
                .select("li");
    }

    @Override
    String getPageUrlFromElement(Element element) {
        return element.selectFirst(".title > a").attr("abs:href");
    }

    @Override
    int findAnnouncementDivNumberOnPage(Document page, String url) {
        Elements liElementsWithData = getElementsWithDataFromPage(page);
        for (int i = 0; i < liElementsWithData.size(); ++i) {
            String elementUrl = getPageUrlFromElement(liElementsWithData.get(i));
            if (elementUrl.equals(url)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    int getNumberOfTotalPages(Document scannedPage) {
        String attr = scannedPage.selectFirst(".last.follows").attr("abs:href");
        Pattern pattern = Pattern.compile("page-[0-9]+");
        Matcher matcher = pattern.matcher(attr);
        if (matcher.find()) {
            String pageNumber = matcher.group().split("-")[1];
            return Integer.parseInt(pageNumber);
        }
        return 0;
    }

    @Override
    String getNextPageUrl(String previousUrl, int nextPageNumber) {
        if (previousUrl == null) {
            return BASE_ANNOUNCEMENTS_URL;
        }
        String[] splitted = previousUrl.split("(krakow/)(page-[0-9]+/)*");
        return splitted[0] + "krakow/page-" + nextPageNumber + "/" + splitted[1].replaceAll("p[0-9]+", "p" + nextPageNumber);
    }

    @Override
    public void updateLastParsedAnnouncement() {
        Optional<ParsingInfo> lastParsed = parsingInfoService
                .fetchLastRecordForProvider("GUMTREE", null);

        lastParsed.ifPresent(parsingInfo -> lastParsedAnnouncement = parsingInfo);
    }
}
