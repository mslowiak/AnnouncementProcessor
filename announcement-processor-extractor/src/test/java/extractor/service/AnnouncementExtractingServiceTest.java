package extractor.service;

import extractor.consumer.AnnouncementConsumer;
import extractor.consumer.AnnouncementConsumerFromString;
import extractor.dto.AnnouncementDto;
import extractor.entity.Announcement;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnnouncementExtractingServiceTest {

    private final AnnouncementConsumer announcementConsumer = new AnnouncementConsumerFromString();
    private final AnnouncementExtractingService extractingService = new AnnouncementExtractingService();

    @Test
    public void extractFromAnnouncementDtoTest() {
        AnnouncementDto announcementDto = announcementConsumer.consumeAnnouncement();
        Announcement announcement = extractingService.extractFromAnnouncementDto(announcementDto);
        System.out.println(announcement);
    }

    @Test
    public void parsingTest() {

        Map<String, BigDecimal> pricesMap = new HashMap<>();
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

        ArrayList<String> utilities = new ArrayList<>();
        utilities.add("gaz");
        utilities.add("prąd");
        utilities.add("czynsz");
        utilities.add("ogrzewanie");
        utilities.add("CO");
        utilities.add("śmieci");
        utilities.add("media");
        utilities.add("internet");
        utilities.add("woda");

        for (String word : utilities) {
            Pattern p = Pattern.compile("\\b" + word);
            Matcher m = p.matcher(description);
            while (m.find()) {
                BigDecimal foundCost = checkNeighbor(description, m.end());
                if (!pricesMap.containsKey(word) || pricesMap.get(word) == null) {
                    pricesMap.put(word, foundCost);
                }
            }
        }
        System.out.println(pricesMap);
    }

    private BigDecimal checkNeighbor(String description, int end) {

        int i = end + 1;
        int counter = 25;
        boolean notFound = true;
        String digitBuilder = "";
        while (notFound && i <= description.length() - 1 && counter >= 0) {
            char ch = description.charAt(i);
            if (Character.isDigit(ch)) {
                digitBuilder = digitBuilder + ch;
            } else {
                counter--;
                if (digitBuilder.length() > 0) {
                    return new BigDecimal(digitBuilder);
                }
            }

            i++;
        }
        return null;
    }
}
