package parser.walker.helpers;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import parser.registry.ParsingInfo;
import parser.registry.ParsingInfoService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public Optional<HashMap<String, Object>> findPageWithAnnouncement(ParsingInfo parsingInfoToFind) {
        HashMap<String, Object> returnValues = null;

        if (parsingInfoToFind != null) {
            try {
                String urlToScan = null;
                HashMap<String, Object> pageToBeFound;
                int scannedPageNumber = 0;
                int totalPage;

                do {
                    scannedPageNumber++;
                    urlToScan = getNextPageUrl(urlToScan, scannedPageNumber);
                    Document scannedPage = getPageAsDocumentFromUrl(urlToScan);
                    pageToBeFound = findAnnouncementUrlOnPage(scannedPage, parsingInfoToFind.getUrl());
                    totalPage = getNumberOfTotalPages(scannedPage);
                } while (pageToBeFound == null && scannedPageNumber <= totalPage);

                if (pageToBeFound != null) {
                    log.debug("\nURL: " + parsingInfoToFind.getUrl() + "\n\t at page: " + scannedPageNumber);
                    returnValues = new HashMap<>();
                    returnValues.put("url", urlToScan);
                    returnValues.put("pageDocument", pageToBeFound.get("pageDocument"));
                    returnValues.put("divNumber", pageToBeFound.get("divNumber"));
                    returnValues.put("scannedPageNumber", scannedPageNumber);
                }
            } catch (IOException e) {
                log.error("Error during finding page with announcement with url: " + parsingInfoToFind.getUrl());
            }
        }
        return Optional.ofNullable(returnValues);
    }

    @Override
    String getPageUrlFromElement(Element element) {
        return element.selectFirst(".title > a").attr("abs:href");
    }

    @Override
    HashMap<String, Object> findAnnouncementUrlOnPage(Document page, String url) {
        Elements liElementsWithData = page
                .select(".view").get(divElementWithAnnouncementsNumber)
                .selectFirst("ul")
                .select("li");
        for (int i = 0; i < liElementsWithData.size(); ++i) {
            String elementUrl = getPageUrlFromElement(liElementsWithData.get(i));
            if (elementUrl.equals(url)) {
                HashMap<String, Object> returnMap = new HashMap<>();
                returnMap.put("pageDocument", page);
                returnMap.put("divNumber", i);
                return returnMap;
            }
        }
        return null;
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
}
