package parser.scrapper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import parser.ReaderUtil;
import parser.exceptions.GumtreePageParseException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GumtreeParserRequiredFieldsTest {

    private GumtreeAnnouncementParser gumtreeParser;
    private Document spyDoc;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        gumtreeParser = new GumtreeAnnouncementParser();
        spyDoc = ReaderUtil.getDocumentToTest("/gumtree/sample-announcement-without-phone.html");
    }

    @Test(expected = GumtreePageParseException.class)
    public void throwGumtreePageParseExceptionInParsePage() throws IOException {
        // given
        AnnouncementParser spy = Mockito.spy(new GumtreeAnnouncementParser());

        // when
        Mockito.doThrow(GumtreePageParseException.class).when(spy).getPageContent(Mockito.anyString());

        // then
        spy.parsePage("https://www.gumtree.pl/a-mieszkania-i-domy-do-wynajecia/krakow/ul-kapelanka-d%C4%99bniki-trzy-pokoje-61-m2/1003845275080911021282609");
    }

    @Test
    public void testParseTitle() {
        // given
        Element titleElement = spyDoc.selectFirst(".item-title");

        // when
        String title = gumtreeParser.parseTitle(titleElement);

        // then
        Assert.assertEquals(GumtreeExpectedProperties.expectedTitle, title);
    }

    @Test
    public void testParseTitleNull() {
        // given
        Element titleElement = null;

        // when
        String title = gumtreeParser.parseTitle(titleElement);

        // then
        Assert.assertNull(title);
    }

    @Test
    public void testParsePrice() {
        // given
        Element priceElement = spyDoc.selectFirst(".price");

        // when
        BigDecimal price = gumtreeParser.parsePrice(priceElement);

        // then
        Assert.assertEquals(GumtreeExpectedProperties.expectedPrice, price);
    }

    @Test
    public void testParsePriceNull() {
        // when
        BigDecimal price = gumtreeParser.parsePrice(null);

        // then
        Assert.assertEquals(BigDecimal.ZERO, price);
    }

    @Test
    public void testLessorName() {
        // given
        Element lessorNameElement = spyDoc.selectFirst(".username");

        // when
        String lessorName = gumtreeParser.parseLessorName(lessorNameElement);

        // then
        Assert.assertEquals(GumtreeExpectedProperties.expectedLessorName, lessorName);
    }

    @Test
    public void testLessorNameNull() {
        // when
        String lessorName = gumtreeParser.parseLessorName(null);

        // then
        Assert.assertNull(lessorName);
    }

    @Test
    public void testCreationDate() {
        // given
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        LocalDateTime dateTime = gumtreeParser.parseCreationDate(detailsElement);

        // then
        Assert.assertEquals(GumtreeExpectedProperties.expectedCreationDateTime, dateTime);
    }

    @Test
    public void testCreationDateNull() {
        // when
        LocalDateTime dateTime = gumtreeParser.parseCreationDate(null);

        // then
        Assert.assertNull(dateTime);
    }

    @Test
    public void testDescription() {
        // given
        Element descriptionElement = spyDoc.selectFirst(".description");

        // when
        String description = gumtreeParser.parseDescription(descriptionElement);

        // then
        Assert.assertEquals(GumtreeExpectedProperties.expectedDescription, description);
    }

    @Test
    public void testDescriptionNull() {
        // when
        String description = gumtreeParser.parseDescription(null);

        // then
        Assert.assertNull(description);
    }
}
