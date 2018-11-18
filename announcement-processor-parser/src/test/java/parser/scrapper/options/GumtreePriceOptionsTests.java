package parser.scrapper.options;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.ReaderUtil;
import parser.scrapper.GumtreeAnnouncementParser;

import java.math.BigDecimal;

public class GumtreePriceOptionsTests {

    private GumtreeAnnouncementParser gumtreeParser;

    @Before
    public void setUp() {
        gumtreeParser = new GumtreeAnnouncementParser();
    }

    @Test
    public void testPriceExchange() {
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/price/exchange.html");
        Element priceElement = spyDoc.selectFirst(".price");

        // when
        BigDecimal price = gumtreeParser.parsePrice(priceElement);

        // then
        Assert.assertEquals(BigDecimal.valueOf(-1), price);
    }

    @Test
    public void testPriceValue() {
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/price/value.html");
        Element priceElement = spyDoc.selectFirst(".price");

        // when
        BigDecimal price = gumtreeParser.parsePrice(priceElement);

        // then
        Assert.assertEquals(BigDecimal.valueOf(1300), price);
    }

    @Test
    public void testPriceNeedContact() {
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/price/need-contact.html");
        Element priceElement = spyDoc.selectFirst(".price");

        // when
        BigDecimal price = gumtreeParser.parsePrice(priceElement);

        // then
        Assert.assertNull(price);
    }
}
