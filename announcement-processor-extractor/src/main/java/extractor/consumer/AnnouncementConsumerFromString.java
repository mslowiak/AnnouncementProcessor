package extractor.consumer;

import extractor.dto.AnnouncementDto;
import extractor.service.MapperService;


public class AnnouncementConsumerFromString implements AnnouncementConsumer {

    private MapperService mapperService;
    private final String json = "{\"title\":\"3 pok. 66 m2 na ul. RAKOWICKIEJ - NOVUM w stylu Skandynawskim\"," +
            "\"price\":3100," +
            "\"creationDate\":\"2019-01-13T00:00:00\"," +
            "\"lessor\":\"Agencja\"," +
            "\"cityLocation\":\"Kraków\"," +
            "\"propertyType\":\"Mieszkanie\"," +
            "\"flatArea\":66.0," +
            "\"roomAmount\":3," +
            "\"bathAmount\":1," +
            "\"parkingAvailability\":null," +
            "\"isSmokingAllowed\":null," +
            "\"isPetFriendly\":null," +
            "\"lessorName\":\"Beata Stawiarz\"," +
            "\"additionalRentCost\":null" +
            ",\"level\":null," +
            "\"furnishing\":null," +
            "\"description\":\"Kraków. Śródmieście ul. Rakowicka\nApartamenty Novum 5 min. na piechotę " +
            "od Galerii Krakowskiej.\nWYSOKI STANDARD-NOWE-URZĄDZONE W STYLU SKANDYNAWSKIM. Meble wysokiej " +
            "jakości\ndębowe robione na wymiar. Wysokość pomieszczeń 2,80m.\nMieszkanie 3 " +
            "pokojowe o powierzchni 66 m2 składa się z salonu połączonego z " +
            "kuchnią,\ndwóch oddzielnych sypialni, łazienki oraz przedpokoju. W łazience łazience" +
            "\nkabina prysznicowa.\nMieszkanie wyposażone w lodówkę, zmywarkę, płytę indukcyjną\n" +
            "z piekarnikiem, mikrofalę, pralkę z suszarką, telewizor, naczynia, garnki" +
            ", sztućce.\nMieszkanie posiada taras z widokiem na dziedziniec. Piętro 5/5." +
            " Do mieszkania\nprzynależy komórka lokatorska oraz miejsce postojowe w garażu podziemnym " +
            "płatne\n200 zł.\nCena mieszkania to 3\n100zł + ok.\n500 zł czynsz w którym są zaliczki na " +
            "media woda ciepła,\nzimna, CO przy zużyciu większej ilość dopłaty + prąd wg zużycia\nWolne " +
            "od 1 lutego\nKontakt Domolink obsługa nieruchomości osoba prowadząca\nBeata Stawiarz " +
            "Tel. 797 578 988. Oferta ta nie stanowi oferty handlowej w rozumieniu Kodeksu Cywilnego.\"," +
            "\"provider\":\"Gumtree\"," +
            "\"url\":\"https://www.gumtree.pl/a-mieszkania-i-domy-do-wynajecia/krakow/3-p" +
            "ok-66-m2-na-ul-rakowickiej-+-novum-w-stylu-skandynawskim/1003992724520910474413109\"," +
            "\"phoneNumber\":\"797578988\"}";

    public AnnouncementConsumerFromString() {
        this.mapperService = new MapperService(); // pass as argument
    }

    @Override
    public AnnouncementDto consumeAnnouncement() {
        return mapperService.getAnnouncementDtoFromJsonString(this.json);
    }
}
