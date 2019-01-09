package parser.scrapper.options;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.ReaderUtil;
import parser.scrapper.GumtreeAnnouncementParser;

public class GumtreeBathAmountOptionsTests {

    private GumtreeAnnouncementParser gumtreeParser;

    @Before
    public void setUp() {
        gumtreeParser = new GumtreeAnnouncementParser();
    }

    @Test
    public void testBathsOne() {
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/baths/1.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        Integer bathsAmount = gumtreeParser.parseBathAmount(detailsElement);

        // then
        Assert.assertEquals(Integer.valueOf(1), bathsAmount);
    }

    @Test
    public void testBathsTwo(){
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/baths/2.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        Integer bathsAmount = gumtreeParser.parseBathAmount(detailsElement);

        // then
        Assert.assertEquals(Integer.valueOf(2), bathsAmount);
    }

    @Test
    public void testBathsThree(){
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/baths/3.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        Integer bathsAmount = gumtreeParser.parseBathAmount(detailsElement);

        // then
        Assert.assertEquals(Integer.valueOf(3), bathsAmount);
    }

    @Test
    public void testBathsFourOrMore(){
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/baths/4-or-more.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        Integer bathsAmount = gumtreeParser.parseBathAmount(detailsElement);

        // then
        Assert.assertEquals(Integer.valueOf(4), bathsAmount);
    }
}
