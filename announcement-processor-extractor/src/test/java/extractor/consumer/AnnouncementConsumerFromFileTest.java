package extractor.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import extractor.JMSConfig;
import extractor.dto.AnnouncementDto;

import extractor.service.MapperService;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AnnouncementConsumerFromFileTest {

    private final JMSConfig jmsConfig = new JMSConfig();
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final MapperService mapperService = new MapperService(objectMapper);
    private AnnouncementConsumer announcementConsumer = new AnnouncementConsumerFromString(mapperService);

    @Test
    public void consumeAnnouncementTest() {
        String title = "Kawalerka 40m Głowackiego 4 Kraków PRZESTRONNE niskie koszty WIDOK na PANORAMĘ Krakowa POŁUDNIE";
        BigDecimal price = new BigDecimal(1300);
        String strDate = "2018-11-17 00:00:00";
        String images = "https://xd.com/pl";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime creationDate = LocalDateTime.parse(strDate, formatter);
        String lessor = "Właściciel";
        String propertyType = "Mieszkanie";
        Double flatArea = 40.0;
        Integer roomAmount = 1;
        Integer bathAmount = 1;
        String parkingAvailability = "Ulica";
        Boolean isSmokingAllowed = true;
        Boolean isPetFriendly = false;
        String lessorName = "Bartek";
        BigDecimal additionalRentCost = null;
        Integer level = null;
        String furnishing = null;
        String description = "Proponuję mieszkanie na wynajem (kawalerka) o powierzchni 40m2 " +
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
                "Palić papierosy można na dużym balkonie ;)\n\n\nPośrednikom z góry dziękuję za zainteresowanie!";
        String provider = "GUMTREE";
        String url = "https://www.gumtree.pl/a-mieszkania-i-domy-do-wynajecia/krakow/" +
                "kawalerka-40m-glowackiego-4-krakow-przestronne-niskie-koszty-widok-na-panorame-krakowa-poludnie/" +
                "1003555014070910781350209";
        String phoneNumber = null;
        AnnouncementDto template = new AnnouncementDto(
                title,
                images,
                price,
                creationDate,
                lessor,
                propertyType,
                flatArea,
                roomAmount,
                bathAmount,
                parkingAvailability,
                isSmokingAllowed,
                isPetFriendly,
                lessorName,
                additionalRentCost,
                level,
                furnishing,
                description,
                provider,
                url,
                phoneNumber
        );

        AnnouncementDto announcementDto = announcementConsumer.consumeAnnouncement();

        Assert.assertEquals(template, announcementDto);
    }
}
