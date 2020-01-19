package extractor.consumer;

import extractor.dto.AnnouncementDto;
import extractor.service.MapperService;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementConsumerFromString implements AnnouncementConsumer {

    private MapperService mapperService;
    private final String json = "{" +
            "\"title\":" +
            "\"Kawalerka 40m Głowackiego 4 Kraków PRZESTRONNE niskie koszty WIDOK na PANORAMĘ Krakowa POŁUDNIE\"," +
            "\"images\": \"https://xd.com/pl\"," +
            "\"price\": 1300," +
            "\"creationDate\":\"2018-11-17T00:00:00\"," +
            "\"lessor\":\"Właściciel\"," +
            "\"propertyType\":\"Mieszkanie\"," +
            "\"flatArea\":40.0," +
            "\"roomAmount\":1," +
            "\"bathAmount\":1," +
            "\"parkingAvailability\":\"Ulica\"," +
            "\"isSmokingAllowed\":true," +
            "\"isPetFriendly\":false," +
            "\"lessorName\":\"Bartek\"," +
            "\"additionalRentCost\":null," +
            "\"level\":null," +
            "\"furnishing\":null," +
            "\"description\":\"Proponuję mieszkanie na wynajem (kawalerka) o powierzchni 40m2 " +
            "zlokalizowane przy ulicy Głowackiego 4 w Krakowie. Dzielnica Krowodrza." +
            " Rejon Bronowice.\\n\\nKawalerka mieście się na 5 piętrze od strony południowej z" +
            " widokiem na panoramę Krakowa.\\n3 minuty pieszo do przystanków tramwajowych." +
            " Bliskie sąsiedztwo Uniwersytetu Pedagogicznego, AGH, Wydziału Fizyki i Architektury " +
            "PK.\\n\\nBudynek oddany do użytkowania w 2008r. Podłoga z litego drewna, w aneksie kuchennym i" +
            " w łazience płytki ceramiczne. Mieszkanie wyposażone tak jak na zdjęciach: (lodówka, płyta grzewcza," +
            " piekarnik, pralka, stół, krzesła, łóżko 2os., łóżko 1os.)\\nMożliwość usunięcia mebli wg upodobań" +
            " wynajmującego jak i ich dokupienia.\\n\\nNieruchomość posiada przestronny hol i windę. Na parterze " +
            "zlokalizowane są sklepy m. in. Lewiatan - można w pantoflach robić zakupy ;)\\n\\nKoszty najmu " +
            "(miesięczne): wynajem 1500zł, + czynsz administracyjny 250zł, + media wg zużycia " +
            "(woda (~60zł/1os), prąd (~60zł/1os) ," +
            " ogrzewanie, internet (60zł)).\\n\\nSZACOWANY CAŁKOWITY KOSZT wynajmu i eksploatacji miesięcznej:" +
            " 1900-2000zł (2 osoby)\\n\\n2K PLN per month ;)\\nZalety to niskie koszty ogrzewania, w zasadzie " +
            "pomijalne.\\n\\nINTERNET od UPC. Mieszkanie wyposażone w gniazdo RTV i SAT.\\n\\nUmowa min. na 9 miesięcy. " +
            "W razie pytań proszę się nie krępować :)\\n\\nKontak mailowy lub telefoniczny (w rozsądnych godzinach):" +
            "\\n\\n6 0 2 3 2 7 7 0 6\\n\\nnioobi (malpa) op (kropka) pl\\n\\n\\nMożliwość oglądnięcia mieszkania\\n\\n" +
            "Palić papierosy można na dużym balkonie ;)\\n\\n\\nPośrednikom z góry dziękuję za zainteresowanie!\"," +
            "\"provider\":\"GUMTREE\"," +
            "\"url\":\"https://www.gumtree.pl/a-mieszkania-i-domy-do-wynajecia/krakow/" +
            "kawalerka-40m-glowackiego-4-krakow-przestronne-niskie-koszty-widok-na-panorame-krakowa-poludnie/" +
            "1003555014070910781350209\"," +
            "\"phoneNumber\":null" +
            "}";

    public AnnouncementConsumerFromString(MapperService mapperService) {
        this.mapperService = mapperService;
    }

    @Override
    public AnnouncementDto consumeAnnouncement() {
        return mapperService.getAnnouncementDtoFromJsonString(this.json);
    }
}
