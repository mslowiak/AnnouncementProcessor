package parser.scrapper.options;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.ReaderUtil;
import parser.scrapper.GumtreeAnnouncementParser;

public class GumtreePropertyTypeOptionsTests {

    private GumtreeAnnouncementParser gumtreeParser;

    @Before
    public void setUp() {
        gumtreeParser = new GumtreeAnnouncementParser();
    }

    @Test
    public void testPropertyTypeFlat() {
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/property-type/flat.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        String propertyType = gumtreeParser.parsePropertyType(detailsElement);

        // then
        Assert.assertEquals("Mieszkanie", propertyType);
    }

    @Test
    public void testPropertyTypeHouse(){
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/property-type/house.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        String propertyType = gumtreeParser.parsePropertyType(detailsElement);

        // then
        Assert.assertEquals("Dom", propertyType);
    }

    @Test
    public void testPropertyTypeOther(){
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/property-type/other.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        String propertyType = gumtreeParser.parsePropertyType(detailsElement);

        // then
        Assert.assertEquals("Inne", propertyType);
    }
}
