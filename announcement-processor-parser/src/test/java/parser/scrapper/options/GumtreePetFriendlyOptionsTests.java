package parser.scrapper.options;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.ReaderUtil;
import parser.scrapper.GumtreeAnnouncementParser;

public class GumtreePetFriendlyOptionsTests {

    private GumtreeAnnouncementParser gumtreeParser;

    @Before
    public void setUp() {
        gumtreeParser = new GumtreeAnnouncementParser();
    }

    @Test
    public void testPetFriendlyYes(){
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/pet-friendly/yes.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        Boolean petFriendly = gumtreeParser.parsePetFriendly(detailsElement);

        // then
        Assert.assertTrue(petFriendly);
    }

    @Test
    public void testPetFriendlyNo(){
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/pet-friendly/no.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        Boolean petFriendly = gumtreeParser.parsePetFriendly(detailsElement);

        // then
        Assert.assertFalse(petFriendly);
    }
}
