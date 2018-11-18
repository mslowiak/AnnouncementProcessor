package parser.scrapper.options;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.ReaderUtil;
import parser.scrapper.GumtreeAnnouncementParser;

public class GumtreeLessorOptionsTests {

    private GumtreeAnnouncementParser gumtreeParser;

    @Before
    public void setUp() {
        gumtreeParser = new GumtreeAnnouncementParser();
    }

    @Test
    public void testLessorOwner() {
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/lessor/owner.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        String lessor = gumtreeParser.parseLessor(detailsElement);

        // then
        Assert.assertEquals("Właściciel", lessor);
    }

    @Test
    public void testLessorAgency() {
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/lessor/agency.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        String lessor = gumtreeParser.parseLessor(detailsElement);

        // then
        Assert.assertEquals("Agencja", lessor);
    }
}
