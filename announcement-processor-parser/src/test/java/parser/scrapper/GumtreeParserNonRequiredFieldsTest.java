package parser.scrapper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.ReaderUtil;


public class GumtreeParserNonRequiredFieldsTest {

    private GumtreeAnnouncementParser gumtreeParser;

    @Before
    public void setUp() {
        gumtreeParser = new GumtreeAnnouncementParser();
    }

    @Test
    public void testPhoneNumberFieldNotExists() {
        // given
        Document spyDocWithoutPhone = ReaderUtil.getDocumentToTest("/gumtree/sample-announcement-without-phone.html");
        Element contactElement = spyDocWithoutPhone.selectFirst(".vip.vip-contact");

        // when
        String phoneNumber = gumtreeParser.parsePhoneNumber(contactElement);

        // then
        Assert.assertEquals(GumtreeExpectedProperties.expectedPhoneNumberNotExists, phoneNumber);
    }

    @Test
    public void testPhoneNumberFieldExists() {
        // given
        Document spyDocWithoutPhone = ReaderUtil.getDocumentToTest("/gumtree/sample-announcement-with-phone.html");
        Element contactElement = spyDocWithoutPhone.selectFirst(".vip.vip-contact");

        // when
        String phoneNumber = gumtreeParser.parsePhoneNumber(contactElement);

        // then
        Assert.assertEquals(GumtreeExpectedProperties.expectedPhoneNumberExists, phoneNumber);
    }

    @Test
    public void testPhoneNumberElementNull() {
        // when
        String phoneNumber = gumtreeParser.parsePhoneNumber(null);

        // then
        Assert.assertNull(phoneNumber);
    }

    @Test
    public void testLessorFieldExists() {
        // given
        Document spyDocWithLessor = ReaderUtil.getDocumentToTest("/gumtree/sample-details-box-with-all-elements.html");
        Element detailsElement = spyDocWithLessor.selectFirst(".vip-details");

        // when
        String lessor = gumtreeParser.parseLessor(detailsElement);

        // then
        Assert.assertEquals(GumtreeExpectedProperties.expectedLessor, lessor);
    }

    @Test
    public void testLessorFieldNotExists() {
        // given
        Document spyDocWithoutLessor = ReaderUtil.getDocumentToTest("/gumtree/sample-details-box-without-any-elements.html");
        Element detailsElement = spyDocWithoutLessor.selectFirst(".vip-details");

        // when
        String lessor = gumtreeParser.parseLessor(detailsElement);

        // then
        Assert.assertNull(lessor);
    }

    @Test
    public void testLessorFieldElementNull() {
        // when
        String lessor = gumtreeParser.parseLessor(null);

        // then
        Assert.assertNull(lessor);
    }

    @Test
    public void testPropertyTypeFieldExists() {
        // given
        Document spyDocWithoutLessor = ReaderUtil.getDocumentToTest("/gumtree/sample-details-box-with-all-elements.html");
        Element detailsElement = spyDocWithoutLessor.selectFirst(".vip-details");

        // when
        String propertyType = gumtreeParser.parsePropertyType(detailsElement);

        // then
        Assert.assertEquals(GumtreeExpectedProperties.expectedPropertyType, propertyType);
    }

    @Test
    public void testPropertyTypeFieldNotExists() {
        // given
        Document spyDocWithoutLessor = ReaderUtil.getDocumentToTest("/gumtree/sample-details-box-without-any-elements.html");
        Element detailsElement = spyDocWithoutLessor.selectFirst(".vip-details");

        // when
        String propertyType = gumtreeParser.parsePropertyType(detailsElement);

        // then
        Assert.assertNull(propertyType);
    }

    @Test
    public void testPropertyTypeElementNull() {
        // when
        String propertyType = gumtreeParser.parsePropertyType(null);

        // then
        Assert.assertNull(propertyType);
    }

    @Test
    public void testFlatAreaFieldExists() {
        // given
        Document spyDocWithoutLessor = ReaderUtil.getDocumentToTest("/gumtree/sample-details-box-with-all-elements.html");
        Element detailsElement = spyDocWithoutLessor.selectFirst(".vip-details");

        // when
        Double flatArea = gumtreeParser.parseFlatArea(detailsElement);

        // then
        Assert.assertEquals(GumtreeExpectedProperties.expectedFlatArea, flatArea);
    }

    @Test
    public void testFlatAreaFieldNotExists() {
        // given
        Document spyDocWithoutLessor = ReaderUtil.getDocumentToTest("/gumtree/sample-details-box-without-any-elements.html");
        Element detailsElement = spyDocWithoutLessor.selectFirst(".vip-details");

        // when
        Double flatArea = gumtreeParser.parseFlatArea(detailsElement);

        // then
        Assert.assertNull(flatArea);
    }

    @Test
    public void testFlatAreaElementNull() {
        // when
        Double flatArea = gumtreeParser.parseFlatArea(null);

        // then
        Assert.assertNull(flatArea);
    }

    @Test
    public void testRoomAmountFieldExists() {
        // given
        Document spyDocWithoutLessor = ReaderUtil.getDocumentToTest("/gumtree/sample-details-box-with-all-elements.html");
        Element detailsElement = spyDocWithoutLessor.selectFirst(".vip-details");

        // when
        Integer roomAmount = gumtreeParser.parseRoomAmount(detailsElement);

        // then
        Assert.assertEquals(GumtreeExpectedProperties.expectedRoomAmount, roomAmount);
    }

    @Test
    public void testRoomAmountFieldNotExists() {
        // given
        Document spyDocWithoutLessor = ReaderUtil.getDocumentToTest("/gumtree/sample-details-box-without-any-elements.html");
        Element detailsElement = spyDocWithoutLessor.selectFirst(".vip-details");

        // when
        Integer roomAmount = gumtreeParser.parseRoomAmount(detailsElement);

        // then
        Assert.assertNull(roomAmount);
    }

    @Test
    public void testRoomAmountElementNull() {
        // when
        Integer roomAmount = gumtreeParser.parseRoomAmount(null);

        // then
        Assert.assertNull(roomAmount);
    }

    @Test
    public void testBathAmountFieldExists() {
        // given
        Document spyDocWithoutLessor = ReaderUtil.getDocumentToTest("/gumtree/sample-details-box-with-all-elements.html");
        Element detailsElement = spyDocWithoutLessor.selectFirst(".vip-details");

        // when
        Integer bathAmount = gumtreeParser.parseBathAmount(detailsElement);

        // then
        Assert.assertEquals(GumtreeExpectedProperties.expectedBathAmount, bathAmount);
    }

    @Test
    public void testBathAmountFieldNotExists() {
        // given
        Document spyDocWithoutLessor = ReaderUtil.getDocumentToTest("/gumtree/sample-details-box-without-any-elements.html");
        Element detailsElement = spyDocWithoutLessor.selectFirst(".vip-details");

        // when
        Integer bathAmount = gumtreeParser.parseBathAmount(detailsElement);

        // then
        Assert.assertNull(bathAmount);
    }

    @Test
    public void testBathAmountElementNull() {
        // when
        Integer bathAmount = gumtreeParser.parseBathAmount(null);

        // then
        Assert.assertNull(bathAmount);
    }

    @Test
    public void testParkingFieldExists() {
        // given
        Document spyDocWithoutLessor = ReaderUtil.getDocumentToTest("/gumtree/sample-details-box-with-all-elements.html");
        Element detailsElement = spyDocWithoutLessor.selectFirst(".vip-details");

        // when
        String parking = gumtreeParser.parseParking(detailsElement);

        // then
        Assert.assertEquals(GumtreeExpectedProperties.expectedParking, parking);
    }

    @Test
    public void testParkingFieldNotExists() {
        // given
        Document spyDocWithoutLessor = ReaderUtil.getDocumentToTest("/gumtree/sample-details-box-without-any-elements.html");
        Element detailsElement = spyDocWithoutLessor.selectFirst(".vip-details");

        // when
        String parking = gumtreeParser.parseParking(detailsElement);

        // then
        Assert.assertNull(parking);
    }

    @Test
    public void testParkingFieldElementNull() {
        // when
        String parking = gumtreeParser.parseParking(null);

        // then
        Assert.assertNull(parking);
    }

    @Test
    public void testSmokersFieldExists() {
        // given
        Document spyDocWithoutLessor = ReaderUtil.getDocumentToTest("/gumtree/sample-details-box-with-all-elements.html");
        Element detailsElement = spyDocWithoutLessor.selectFirst(".vip-details");

        // when
        Boolean smokers = gumtreeParser.parseSmokers(detailsElement);

        // then
        Assert.assertEquals(GumtreeExpectedProperties.expectedSmokers, smokers);
    }

    @Test
    public void testSmokersFieldNotExists() {
        // given
        Document spyDocWithoutLessor = ReaderUtil.getDocumentToTest("/gumtree/sample-details-box-without-any-elements.html");
        Element detailsElement = spyDocWithoutLessor.selectFirst(".vip-details");

        // when
        Boolean smokers = gumtreeParser.parseSmokers(detailsElement);

        // then
        Assert.assertNull(smokers);
    }

    @Test
    public void testSmokersFieldElementNull() {
        // when
        Boolean isForSmokers = gumtreeParser.parseSmokers(null);

        // then
        Assert.assertNull(isForSmokers);
    }

    @Test
    public void testPetFriendlyFieldExists() {
        // given
        Document spyDocWithoutLessor = ReaderUtil.getDocumentToTest("/gumtree/sample-details-box-with-all-elements.html");
        Element detailsElement = spyDocWithoutLessor.selectFirst(".vip-details");

        // when
        Boolean petFriendly = gumtreeParser.parsePetFriendly(detailsElement);

        // then
        Assert.assertEquals(GumtreeExpectedProperties.expectedPetFriendly, petFriendly);
    }

    @Test
    public void testPetFriendlyFieldNotExists() {
        // given
        Document spyDocWithoutLessor = ReaderUtil.getDocumentToTest("/gumtree/sample-details-box-without-any-elements.html");
        Element detailsElement = spyDocWithoutLessor.selectFirst(".vip-details");

        // when
        Boolean petFriendly = gumtreeParser.parsePetFriendly(detailsElement);

        // then
        Assert.assertNull(petFriendly);
    }


    @Test
    public void testPetFriendlyElementNull() {
        // when
        Boolean isPetFriendly = gumtreeParser.parsePetFriendly(null);

        // then
        Assert.assertNull(isPetFriendly);
    }
}