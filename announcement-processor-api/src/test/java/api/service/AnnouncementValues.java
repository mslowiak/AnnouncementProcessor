package api.service;

import api.dto.*;
import api.entity.AdditionalCosts;
import api.entity.Lessor;
import api.entity.Location;
import api.entity.PropertyData;
import api.model.DetailedAnnouncementInfo;
import api.model.GeneralAnnouncementInfo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class AnnouncementValues {

    public static final AnnouncementDto ANNOUNCEMENT_DTO = new AnnouncementDto(
            "Kawalerka 40m Głowackiego 4 Kraków PRZESTRONNE niskie koszty WIDOK na PANORAMĘ Krakowa POŁUDNIE",
            "https://xd.com/pl",
            new PriceDto(BigDecimal.valueOf(1000), "PLN", new HashMap<>()),
            new LocationDto("Polska", "Krakow", null, null, null, null, null),
            new PropertyDataDto("mieszkanie", 45.0, true, false,
                    3, 1, "ulica", 3, "brak"),
            new LessorDto("Dejw", "wlasciciel", "900900900", "mail@mail.pl"),
            LocalDateTime.parse("2018-11-17 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            "https://www.gumtree.pl/a-mieszkania-i-domy-do-wynajecia/krakow/" +
                    "kawalerka-40m-glowackiego-4-krakow-przestronne-niskie-koszty-widok-na-panorame-krakowa-poludnie/\" +\n" +
                    "1003555014070910781350209",
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
            "GUMTREE"
    );

    public static final GeneralAnnouncementInfo GENERAL_ANNOUNCEMENT_INFO = new GeneralAnnouncementInfo(
            10,
            "Kawalerka 40m Głowackiego 4 Kraków PRZESTRONNE niskie koszty WIDOK na PANORAMĘ Krakowa POŁUDNIE",
            "https://xd.com/pl",
            BigDecimal.valueOf(1000),
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
            LocalDateTime.parse("2018-11-17 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            "https://www.gumtree.pl/a-mieszkania-i-domy-do-wynajecia/krakow/" +
                    "kawalerka-40m-glowackiego-4-krakow-przestronne-niskie-koszty-widok-na-panorame-krakowa-poludnie/\" +\n" +
                    "1003555014070910781350209",
            "wlasciciel",
            "Krakow"
    );

    public static final DetailedAnnouncementInfo DETAILED_ANNOUNCEMENT_INFO = new DetailedAnnouncementInfo(
            "Kawalerka 40m Głowackiego 4 Kraków PRZESTRONNE niskie koszty WIDOK na PANORAMĘ Krakowa POŁUDNIE",
            "https://xd.com/pl",
            "1000",
            "GUMTREE",
            "2018-11-17 00:00:00",
            "https://www.gumtree.pl/a-mieszkania-i-domy-do-wynajecia/krakow/" +
                    "kawalerka-40m-glowackiego-4-krakow-przestronne-niskie-koszty-widok-na-panorame-krakowa-poludnie/\" +\n" +
                    "1003555014070910781350209",
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
            new PropertyData(),
            new Lessor(),
            new ArrayList<AdditionalCosts>(),
            new Location()
    );
}
