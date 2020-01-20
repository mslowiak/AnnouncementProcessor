package extractor.service;

import extractor.dto.AnnouncementDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AnnouncementValue {

    public static final String ANNOUNCEMENTDTO_JSON = "{" +
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

    public static final AnnouncementDto ANNOUNCEMENT_DTO = new AnnouncementDto(
           "Kawalerka 40m Głowackiego 4 Kraków PRZESTRONNE niskie koszty WIDOK na PANORAMĘ Krakowa POŁUDNIE",
            "https://xd.com/pl",
            BigDecimal.valueOf(1300),
            LocalDateTime.parse("2018-11-17 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            "Właściciel",
            "Mieszkanie",
            40.0,
            1,
            1,
            "Ulica",
            true,
            false,
            "Bartek",
            null,
            null,
            null,
            "Proponuję mieszkanie na wynajem (kawalerka) o powierzchni 40m2 " +
                    "zlokalizowane przy ulicy Głowackiego 4 w Krakowie. Dzielnica Krowodrza." +
                    " Rejon Bronowice.\n\nKawalerka mieście się na 5 piętrze od strony południowej z" +
                    " widokiem na panoramę Krakowa.\n3 minuty pieszo do przystanków tramwajowych." +
                    " Bliskie sąsiedztwo Uniwersytetu Pedagogicznego, AGH, Wydziału Fizyki i Architektury " +
                    "PK.\n\nBudynek oddany do użytkowania w 2008r. Podłoga z litego drewna, w aneksie kuchennym i" +
                    " w łazience płytki ceramiczne. Mieszkanie wyposażone tak jak na zdjęciach: (lodówka, płyta grzewcza," +
                    " piekarnik, pralka, stół, krzesła, łóżko 2os., łóżko 1os.)\nMożliwość usunięcia mebli wg upodobań" +
                    " wynajmującego jak i ich dokupienia.\n\nNieruchomość posiada przestronny hol i windę. Na parterze " +
                    "zlokalizowane są sklepy m. in. Lewiatan - można w pantoflach robić zakupy ;)\n\nKoszty najmu " +
                    "(miesięczne): wynajem 1500zł, + czynsz administracyjny 250zł, + media wg zużycia " +
                    "(woda (~60zł/1os), prąd (~60zł/1os) ," +
                    " ogrzewanie, internet (60zł)).\n\nSZACOWANY CAŁKOWITY KOSZT wynajmu i eksploatacji miesięcznej:" +
                    " 1900-2000zł (2 osoby)\n\n2K PLN per month ;)\nZalety to niskie koszty ogrzewania, w zasadzie " +
                    "pomijalne.\n\nINTERNET od UPC. Mieszkanie wyposażone w gniazdo RTV i SAT.\n\nUmowa min. na 9 miesięcy. " +
                    "W razie pytań proszę się nie krępować :)\n\nKontak mailowy lub telefoniczny (w rozsądnych godzinach):" +
                    "\n\n6 0 2 3 2 7 7 0 6\n\nnioobi (malpa) op (kropka) pl\n\n\nMożliwość oglądnięcia mieszkania\n\n" +
                    "Palić papierosy można na dużym balkonie ;)\n\n\nPośrednikom z góry dziękuję za zainteresowanie!",
            "GUMTREE",
            "https://www.gumtree.pl/a-mieszkania-i-domy-do-wynajecia/krakow/" +
                    "kawalerka-40m-glowackiego-4-krakow-przestronne-niskie-koszty-widok-na-panorame-krakowa-poludnie/" +
                    "1003555014070910781350209",
            null
    );

    public static final String ANNOUNCEMENT_JSON = "{\"title\":\"Kawalerka 40m Głowackiego 4 Kraków PRZESTRONNE niskie koszty WIDOK na PANORAMĘ Krakowa POŁUDNIE\"," +
            "\"images\":\"https://xd.com/pl\",\"price\":{\"basePrice\":1300,\"currency\":null," +
            "\"additionalPrices\":{\"woda\":60,\"prąd\":60,\"ogrzewanie\":60,\"media\":60,\"czynsz\":250,\"CO\":null," +
            "\"internet\":60}},\"location\":{\"country\":null,\"city\":null,\"street\":null,\"zipCode\":null," +
            "\"buildingNumber\":null,\"flatNumber\":null,\"district\":null}," +
            "\"propertyData\":{\"propertyType\":\"Mieszkanie\",\"area\":40.0,\"isSmokingAllowed\":true," +
            "\"isPerFriendly\":false,\"roomNumber\":1,\"bathroomNumber\":1,\"parkingAvailability\":\"Ulica\"," +
            "\"level\":null,\"furnishing\":null},\"lessor\":{\"name\":\"Bartek\",\"lessorType\":\"Właściciel\"," +
            "\"phoneNumber\":null,\"email\":null},\"creationDate\":[2018,11,17,0,0]," +
            "\"url\":\"https://www.gumtree.pl/a-mieszkania-i-domy-do-wynajecia/krakow/kawalerka-40m-glowackiego-4-krakow-przestronne-niskie-koszty-widok-na-panorame-krakowa-poludnie/1003555014070910781350209\"," +
            "\"description\":\"Proponuję mieszkanie na wynajem (kawalerka) o powierzchni 40m2 zlokalizowane przy ulicy Głowackiego 4 w Krakowie. Dzielnica Krowodrza. Rejon Bronowice.\\n\\nKawalerka mieście się na 5 piętrze od strony południowej z widokiem na panoramę Krakowa.\\n3 minuty pieszo do przystanków tramwajowych. Bliskie sąsiedztwo Uniwersytetu Pedagogicznego, AGH, Wydziału Fizyki i Architektury PK.\\n\\nBudynek oddany do użytkowania w 2008r. Podłoga z litego drewna, w aneksie kuchennym i w łazience płytki ceramiczne. Mieszkanie wyposażone tak jak na zdjęciach: (lodówka, płyta grzewcza, piekarnik, pralka, stół, krzesła, łóżko 2os., łóżko 1os.)\\nMożliwość usunięcia mebli wg upodobań wynajmującego jak i ich dokupienia.\\n\\nNieruchomość posiada przestronny hol i windę. Na parterze zlokalizowane są sklepy m. in. Lewiatan - można w pantoflach robić zakupy ;)\\n\\nKoszty najmu (miesięczne): wynajem 1500zł, + czynsz administracyjny 250zł, + media wg zużycia (woda (~60zł/1os), prąd (~60zł/1os) , ogrzewanie, internet (60zł)).\\n\\nSZACOWANY CAŁKOWITY KOSZT wynajmu i eksploatacji miesięcznej: 1900-2000zł (2 osoby)\\n\\n2K PLN per month ;)\\nZalety to niskie koszty ogrzewania, w zasadzie pomijalne.\\n\\nINTERNET od UPC. Mieszkanie wyposażone w gniazdo RTV i SAT.\\n\\nUmowa min. na 9 miesięcy. W razie pytań proszę się nie krępować :)\\n\\nKontak mailowy lub telefoniczny (w rozsądnych godzinach):\\n\\n6 0 2 3 2 7 7 0 6\\n\\nnioobi (malpa) op (kropka) pl\\n\\n\\nMożliwość oglądnięcia mieszkania\\n\\nPalić papierosy można na dużym balkonie ;)\\n\\n\\nPośrednikom z góry dziękuję za zainteresowanie!\"," +
            "\"provider\":\"GUMTREE\"}";
}
