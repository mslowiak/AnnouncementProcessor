package parser.scrapper.options;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.ReaderUtil;
import parser.scrapper.GumtreeAnnouncementParser;

public class GumtreeRoomAmountOptionsTest {

    private GumtreeAnnouncementParser gumtreeParser;

    @Before
    public void setUp() {
        gumtreeParser = new GumtreeAnnouncementParser();
    }

    @Test
    public void testRoomAmountOne(){
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/rooms/1.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        Integer roomAmount = gumtreeParser.parseRoomAmount(detailsElement);

        // then
        Assert.assertEquals(Integer.valueOf(1), roomAmount);
    }

    @Test
    public void testRoomAmountTwo(){
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/rooms/2.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        Integer roomAmount = gumtreeParser.parseRoomAmount(detailsElement);

        // then
        Assert.assertEquals(Integer.valueOf(2), roomAmount);
    }

    @Test
    public void testRoomAmountThree(){
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/rooms/3.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        Integer roomAmount = gumtreeParser.parseRoomAmount(detailsElement);

        // then
        Assert.assertEquals(Integer.valueOf(3), roomAmount);
    }

    @Test
    public void testRoomAmountFour(){
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/rooms/4.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        Integer roomAmount = gumtreeParser.parseRoomAmount(detailsElement);

        // then
        Assert.assertEquals(Integer.valueOf(4), roomAmount);
    }

    @Test
    public void testRoomAmountFive(){
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/rooms/5.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        Integer roomAmount = gumtreeParser.parseRoomAmount(detailsElement);

        // then
        Assert.assertEquals(Integer.valueOf(5), roomAmount);
    }

    @Test
    public void testRoomAmountSixOrMore(){
        // given
        Document spyDoc = ReaderUtil.getDocumentToTest("/gumtree/options/rooms/6-or-more.html");
        Element detailsElement = spyDoc.selectFirst(".vip-details");

        // when
        Integer roomAmount = gumtreeParser.parseRoomAmount(detailsElement);

        // then
        Assert.assertEquals(Integer.valueOf(6), roomAmount);
    }
}
