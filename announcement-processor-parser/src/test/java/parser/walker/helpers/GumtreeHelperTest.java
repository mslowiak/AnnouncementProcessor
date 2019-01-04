package parser.walker.helpers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import parser.ReaderUtil;
import parser.registry.ParsingInfoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class GumtreeHelperTest {
    private GumtreeHelper gumtreeHelper;

    @Mock
    private ParsingInfoService parsingInfoService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        gumtreeHelper = new GumtreeHelper(parsingInfoService);
    }

    @Test
    public void findPageWithAnnouncementWhenParsingInfoIsNull() {
        // given

        // when
        Optional<WalkerInfo> pageWithAnnouncement = gumtreeHelper.findPageWithAnnouncement(null);

        // then
        Assert.assertEquals(Optional.empty(), pageWithAnnouncement);
    }

    @Test
    public void findAnnouncementUrlOnPageIsNotNull() {
        // given
        String baseUrl = "https://www.gumtree.pl/s-mieszkania-i-domy-do-wynajecia/krakow/v1c9008l3200208p1";
        String resourcePath = "/gumtree/walker/announcements-list.html";
        Document documentToTest = ReaderUtil.getDocumentToTestWithBaseUrl(resourcePath, baseUrl);

        // when
        int announcementDivNumberOnPage = gumtreeHelper.findAnnouncementDivNumberOnPage(
                documentToTest,
                "https://www.gumtree.pl/a-mieszkania-i-domy-do-wynajecia/krakow/przytulna-kawalerka-do-wynaj%C4%99cia/1003754341000911379548809"
        );

        // then
        Assert.assertNotEquals(-1, announcementDivNumberOnPage);
    }

    @Test
    public void findAnnouncementUrlOnPageIsNull() {
        // given
        String baseUrl = "https://www.gumtree.pl/s-mieszkania-i-domy-do-wynajecia/krakow/v1c9008l3200208p1";
        String resourcePath = "/gumtree/walker/announcements-list.html";
        Document documentToTest = ReaderUtil.getDocumentToTestWithBaseUrl(resourcePath, baseUrl);

        // when
        int announcementDivNumberOnPage = gumtreeHelper.findAnnouncementDivNumberOnPage(documentToTest,
                "https://www.gumtree.pl/a-mieszkania-i-domy-do-wynajecia/krakow/123213213dsadasdsa9");

        // then
        Assert.assertEquals(-1, announcementDivNumberOnPage);
    }

    @Test
    public void getUrlsToParse() {
        // given
        String resourcePath = "/gumtree/walker/announcements-list.html";
        Document documentToTest = ReaderUtil.getDocumentToTest(resourcePath);

        // when
        List<String> urlsToParse = gumtreeHelper.getUrlsToParse(documentToTest, 4);

        // then
        Assert.assertEquals(4, urlsToParse.size());
    }

    @Test
    public void getEarliestDateOnAnnouncementPage() {
        // given
        String resourcePath = "/gumtree/walker/announcements-list.html";
        Document documentToTest = ReaderUtil.getDocumentToTest(resourcePath);
        LocalDate localDate = LocalDate.now();

        // when
        LocalDate returnedDate = gumtreeHelper.getEarliestDateOnAnnouncementPage(documentToTest);

        // then
        Assert.assertEquals(localDate.getMonth(), returnedDate.getMonth());
        Assert.assertEquals(localDate.getDayOfMonth(), returnedDate.getDayOfMonth());
    }

    @Test
    public void fetchLatestRecord() {
        // given

        // when
        gumtreeHelper.fetchLatestRecord();

        // then
        Mockito.verify(parsingInfoService, Mockito.times(1))
                .fetchLastRecordForProvider(Mockito.anyString(), Mockito.any());
    }

    @Test
    public void convertStringToLocalDateWhenMinutesAgoWithDayBreak() {
        // given
        LocalDateTime localDateTime = LocalDateTime.of(2018, 12, 10, 0, 20);
        String date = "30 min temu";

        // when
        LocalDate returnedDate = gumtreeHelper.convertStringToLocalDate(date, localDateTime);

        // then
        Assert.assertEquals(LocalDate.of(2018, 12, 9), returnedDate);
    }

    @Test
    public void convertStringToLocalDateWhenMinutesAgoWithoutDayBreak() {
        // given
        LocalDateTime localDateTime = LocalDateTime.of(2018, 12, 10, 10, 48);
        String date = "11 min temu";

        // when
        LocalDate returnedDate = gumtreeHelper.convertStringToLocalDate(date, localDateTime);

        // then
        Assert.assertEquals(LocalDate.of(2018, 12, 10), returnedDate);
    }

    @Test
    public void convertStringToLocalDateWhenHoursAgoWithDayBreak() {
        // given
        LocalDateTime localDateTime = LocalDateTime.of(2018, 12, 10, 7, 30);
        String date = "7 godz temu";

        // when
        LocalDate returnedDate = gumtreeHelper.convertStringToLocalDate(date, localDateTime);

        // then
        Assert.assertEquals(LocalDate.of(2018, 12, 9), returnedDate);
    }

    @Test
    public void convertStringToLocalDateWhenHoursAgoWithoutDayBreak() {
        // given
        LocalDateTime localDateTime = LocalDateTime.of(2018, 12, 10, 8, 30);
        String date = "7 godz temu";

        // when
        LocalDate returnedDate = gumtreeHelper.convertStringToLocalDate(date, localDateTime);

        // then
        Assert.assertEquals(LocalDate.of(2018, 12, 10), returnedDate);
    }

    @Test
    public void convertStringToLocalDateWhenDateSpecified() {
        // given
        LocalDateTime localDateTime = LocalDateTime.of(2018, 12, 10, 8, 30);
        String date = "4-12";

        // when
        LocalDate returnedDate = gumtreeHelper.convertStringToLocalDate(date, localDateTime);

        // then
        Assert.assertEquals(LocalDate.of(2018, 12, 4), returnedDate);
    }

    @Test
    public void getPageUrlFromElement() {
        // given
        String html = "<div class=\"title\">\n" + "\t\t\t\t\t\t\t\n"
                + "\t\t\t\t\t\t\t    <a class=\"href-link\" href=\"/a-mieszkania-i-domy-do-wynajecia/krakow/nowe-kawalerka-d%C4%99bniki-bezpo%C5%9Brednio-english-deutsch/1003692394800910479699309\">Nowe kawalerka Dębniki bezpośrednio (english / deutsch)</a>\n"
                + "\t\t\t\t\t\t\t\n" + "\t\t\t\t\t\t</div>";
        String baseUrl = "https://www.gumtree.pl/s-mieszkania-i-domy-do-wynajecia/krakow/v1c9008l3200208p1";
        Element element = Jsoup.parse(html, baseUrl).selectFirst("div");
        String expectedPageUrl = "https://www.gumtree.pl/a-mieszkania-i-domy-do-wynajecia/krakow/nowe-kawalerka-d%C4%99bniki-bezpo%C5%9Brednio-english-deutsch/1003692394800910479699309";

        // when
        String returnedPageUrl = gumtreeHelper.getPageUrlFromElement(element);

        // then
        Assert.assertEquals(expectedPageUrl, returnedPageUrl);
    }

    @Test
    public void getNumberOfTotalPagesWithGoodPattern() {
        // given
        String html = "<a class=\"last follows\" href=\"/s-mieszkania-i-domy-do-wynajecia/krakow/page-85/v1c9008l3200208p85\"><span class=\"icon-double-angle-right\"></span></a>";
        String baseUrl = "https://www.gumtree.pl/s-mieszkania-i-domy-do-wynajecia/krakow/page-2/v1c9008l3200208p2";
        Document parsedHtml = Jsoup.parse(html, baseUrl);

        // when
        int numberOfTotalPages = gumtreeHelper.getNumberOfTotalPages(parsedHtml);

        // then
        Assert.assertEquals(85, numberOfTotalPages);
    }

    @Test
    public void getNumberOfTotalPagesIfNoPattern() {
        // given
        String html = "<a class=\"last follows\" href=\"/s-mieszkania-i-domy-do-wynajecia/krakow/v1c9008l3200208p1\"><span class=\"icon-double-angle-right\"></span></a>";
        String baseUrl = "https://www.gumtree.pl/s-mieszkania-i-domy-do-wynajecia/krakow/v1c9008l3200208p1";
        Document parsedHtml = Jsoup.parse(html, baseUrl);

        // when
        int numberOfTotalPages = gumtreeHelper.getNumberOfTotalPages(parsedHtml);

        // then
        Assert.assertEquals(0, numberOfTotalPages);
    }

    @Test
    public void getNextPageUrlWhenPreviousUrlNull() {
        // given
        String BASE_ANNOUNCEMENTS_URL = "https://www.gumtree.pl/s-mieszkania-i-domy-do-wynajecia/krakow/v1c9008l3200208p1";

        // when
        String returnedUrl = gumtreeHelper.getNextPageUrl(null, 2);

        // then
        Assert.assertEquals(BASE_ANNOUNCEMENTS_URL, returnedUrl);
    }

    @Test
    public void getNextPageUrlWhenPreviousUrlNotNull() {
        // given
        String previousUrl = "https://www.gumtree.pl/s-mieszkania-i-domy-do-wynajecia/krakow/page-2/v1c9008l3200208p2";
        String expectedUrl = "https://www.gumtree.pl/s-mieszkania-i-domy-do-wynajecia/krakow/page-3/v1c9008l3200208p3";

        // when
        String returnedUrl = gumtreeHelper.getNextPageUrl(previousUrl, 3);

        // then
        Assert.assertEquals(expectedUrl, returnedUrl);
    }

    @Test
    public void getPreviousPageUrlWhenActualUrlIsNull() {
        // when
        String returnedUrl = gumtreeHelper.getPreviousPageUrl(null);

        // then
        Assert.assertNull(returnedUrl);
    }

    @Test
    public void getPreviousPageUrlWhenActualUrlIsNotNullAndOverOne() {
        // given
        String previousUrl = "https://www.gumtree.pl/s-mieszkania-i-domy-do-wynajecia/krakow/page-3/v1c9008l3200208p3";
        String expectedUrl = "https://www.gumtree.pl/s-mieszkania-i-domy-do-wynajecia/krakow/page-2/v1c9008l3200208p2";

        // when
        String returnedUrl = gumtreeHelper.getPreviousPageUrl(previousUrl);

        // then
        Assert.assertEquals(expectedUrl, returnedUrl);
    }

    @Test
    public void getPreviousPageUrlWhenActualUrlIsOne() {
        // given
        String previousUrl = "https://www.gumtree.pl/s-mieszkania-i-domy-do-wynajecia/krakow/page-1/v1c9008l3200208p1";

        // when
        String returnedUrl = gumtreeHelper.getPreviousPageUrl(previousUrl);

        // then
        Assert.assertNull(returnedUrl);
    }
}
