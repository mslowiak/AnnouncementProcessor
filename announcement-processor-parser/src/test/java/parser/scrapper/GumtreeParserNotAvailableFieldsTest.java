package parser.scrapper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;
import parser.ReaderUtil;
import parser.exceptions.PropertyNotValidForGumtreeProviderException;

public class GumtreeParserNotAvailableFieldsTest {
    private GumtreeAnnouncementParser gumtreeParser;

    @Before
    public void setUp() {
        gumtreeParser = new GumtreeAnnouncementParser();
    }

    @Test(expected = PropertyNotValidForGumtreeProviderException.class)
    public void testAdditionalRentCostsField() {
        // given
        Document spyDocWithoutPhone = ReaderUtil.getDocumentToTest("/gumtree/sample-announcement-without-phone.html");
        Element element = spyDocWithoutPhone.selectFirst(".vip.vip-contact");

        // when
        gumtreeParser.parseAdditionalRentCost(element);
    }

    @Test(expected = PropertyNotValidForGumtreeProviderException.class)
    public void testLevelField() {
        // given
        Document spyDocWithoutPhone = ReaderUtil.getDocumentToTest("/gumtree/sample-announcement-without-phone.html");
        Element element = spyDocWithoutPhone.selectFirst(".vip.vip-contact");

        // when
        gumtreeParser.parseLevel(element);
    }

    @Test(expected = PropertyNotValidForGumtreeProviderException.class)
    public void testFurnishingsField() {
        // given
        Document spyDocWithoutPhone = ReaderUtil.getDocumentToTest("/gumtree/sample-announcement-without-phone.html");
        Element element = spyDocWithoutPhone.selectFirst(".vip.vip-contact");

        // when
        gumtreeParser.parseFurnishings(element);
    }
}
