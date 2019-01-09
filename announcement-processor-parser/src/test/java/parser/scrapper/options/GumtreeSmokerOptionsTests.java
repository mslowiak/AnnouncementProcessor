package parser.scrapper.options;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.ReaderUtil;
import parser.scrapper.GumtreeAnnouncementParser;

public class GumtreeSmokerOptionsTests {

    private GumtreeAnnouncementParser gumtreeParser;

    @Before
    public void setUp() {
        gumtreeParser = new GumtreeAnnouncementParser();
    }

    @Test
    public void testSmokersYes(){
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/smoker/yes.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        Boolean smokers = gumtreeParser.parseSmokers(detailsElement);

        // then
        Assert.assertTrue(smokers);
    }

    @Test
    public void testSmokersNo(){
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/smoker/no.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        Boolean smokers = gumtreeParser.parseSmokers(detailsElement);

        // then
        Assert.assertFalse(smokers);
    }
}
