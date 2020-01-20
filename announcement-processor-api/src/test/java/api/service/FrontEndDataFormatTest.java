package api.service;

import api.converter.AnnouncementDtoEntityConverter;
import api.entity.Announcement;
import api.entity.Currency;
import api.model.GeneralAnnouncementInfo;
import api.repository.AnnouncementRepository;
import api.repository.CurrencyRepository;
import api.repository.SearchRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

public class FrontEndDataFormatTest {

    private ExtractorDataService extractorDataService;
    private CurrencyService currencyService;
    private FrontEndDataService frontEndDataService;
    private ModelMapper mapper = new ModelMapper();

    @Mock
    private SearchRepository searchRepository;

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private AnnouncementRepository announcementRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        extractorDataService = new ExtractorDataService(mapper, announcementRepository);
        Optional<Currency> currencyOptional = Optional.of(new Currency(10, "EUR", "PLN", BigDecimal.valueOf(4)));
        Optional<Announcement> announcement = Optional.of(extractorDataService.convertAndSave(AnnouncementValues.ANNOUNCEMENT_DTO));

        Mockito.when(currencyRepository.findCurrencyByCurrencyFromAndCurrencyTo("EUR", "PLN"))
                .thenReturn(currencyOptional);
        Mockito.when(announcementRepository.findById(Mockito.anyInt()))
                .thenReturn(announcement);

        mapper.addConverter(new AnnouncementDtoEntityConverter());
        currencyService = new CurrencyService(currencyRepository);


        frontEndDataService = new FrontEndDataService(announcementRepository, currencyService, searchRepository);
    }

    @Test
    public void getDetailedInfoAnnouncementTest() {
        GeneralAnnouncementInfo expected = AnnouncementValues.GENERAL_ANNOUNCEMENT_INFO;

        GeneralAnnouncementInfo generalInfoAnnouncement = frontEndDataService.getGeneralInfoAnnouncement(10, "PLN");

        Assert.assertEquals(expected.getTitle(), generalInfoAnnouncement.getTitle());
        Assert.assertEquals(expected.getUrl(), generalInfoAnnouncement.getUrl());
        Assert.assertEquals(expected.getCreationDate(), generalInfoAnnouncement.getCreationDate());
    }
}
