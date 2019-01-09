package parser.registry;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.time.LocalDate;
import java.util.Optional;


public class ParsingInfoServiceTest {

    private ParsingInfoService parsingInfoService;

    @Mock
    public ParsingInfoRepository parsingInfoRepository;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        parsingInfoService = new ParsingInfoService(parsingInfoRepository);
    }

    @Test
    public void testFetchingLastRecordOnFirstCall() {
        // given
        String provider = "GUMTREE";
        ParsingInfo lastParsedAnnouncement = null;

        // when
        parsingInfoService.fetchLastRecordForProvider(provider, lastParsedAnnouncement);

        // then
        Mockito.verify(parsingInfoRepository, Mockito.times(1))
                .findFirstByProviderOrderByDateDescCounterPerDateDesc(provider);
    }

    @Test
    public void testFetchingLastRecordWhenNoMoreRecordsFromDay() {
        // given
        String provider = "GUMTREE";
        ParsingInfo lastParsedAnnouncement = new ParsingInfo();
        lastParsedAnnouncement.setDate(LocalDate.now());
        lastParsedAnnouncement.setCounterPerDate(1);

        // when
        Mockito.when(
                parsingInfoRepository.findFirstByProviderAndDateEqualsAndCounterPerDateLessThanOrderByCounterPerDateDesc(
                        provider,
                        lastParsedAnnouncement.getDate(),
                        lastParsedAnnouncement.getCounterPerDate()
                )
        ).thenReturn(Optional.empty());
        parsingInfoService.fetchLastRecordForProvider(provider, lastParsedAnnouncement);

        // then
        Mockito.verify(parsingInfoRepository, Mockito.times(1))
                .findFirstByProviderAndDateEqualsAndCounterPerDateLessThanOrderByCounterPerDateDesc(
                        Mockito.anyString(),
                        Mockito.any(),
                        Mockito.anyInt()
                );
        Mockito.verify(parsingInfoRepository, Mockito.times(1))
                .findFirstByProviderAndDateLessThanOrderByDateDescCounterPerDateDesc(
                        Mockito.anyString(),
                        Mockito.any()
                );
    }

    @Test
    public void testFetchingLastRecordWhenMoreRecordsFromDay() {
        // given
        String provider = "GUMTREE";
        ParsingInfo lastParsedAnnouncement = new ParsingInfo();
        lastParsedAnnouncement.setDate(LocalDate.now());
        lastParsedAnnouncement.setCounterPerDate(1);

        // when
        Mockito.when(
                parsingInfoRepository.findFirstByProviderAndDateEqualsAndCounterPerDateLessThanOrderByCounterPerDateDesc(
                        provider,
                        lastParsedAnnouncement.getDate(),
                        lastParsedAnnouncement.getCounterPerDate()
                )
        ).thenReturn(Optional.of(new ParsingInfo()));
        parsingInfoService.fetchLastRecordForProvider(provider, lastParsedAnnouncement);

        // then
        Mockito.verify(parsingInfoRepository, Mockito.times(1))
                .findFirstByProviderAndDateEqualsAndCounterPerDateLessThanOrderByCounterPerDateDesc(
                        Mockito.anyString(),
                        Mockito.any(),
                        Mockito.anyInt()
                );
        Mockito.verify(parsingInfoRepository, Mockito.times(0))
                .findFirstByProviderAndDateLessThanOrderByDateDescCounterPerDateDesc(
                        Mockito.anyString(),
                        Mockito.any()
                );
    }
}