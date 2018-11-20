package parser.scrapper.options;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.ReaderUtil;
import parser.scrapper.GumtreeAnnouncementParser;

public class GumtreeParkingOptionsTests {

    private GumtreeAnnouncementParser gumtreeParser;

    @Before
    public void setUp() {
        gumtreeParser = new GumtreeAnnouncementParser();
    }

    @Test
    public void testParkingAbsence() {
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/parking/absence.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        String parking = gumtreeParser.parseParking(detailsElement);

        // then
        Assert.assertEquals("Brak", parking);
    }

    @Test
    public void testParkingCovered() {
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/parking/covered.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        String parking = gumtreeParser.parseParking(detailsElement);

        // then
        Assert.assertEquals("Kryty", parking);
    }

    @Test
    public void testParkingGarage() {
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/parking/garage.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        String parking = gumtreeParser.parseParking(detailsElement);

        // then
        Assert.assertEquals("Garaż", parking);
    }

    @Test
    public void testParkingStreet() {
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/parking/street.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        String parking = gumtreeParser.parseParking(detailsElement);

        // then
        Assert.assertEquals("Ulica", parking);
    }
}
