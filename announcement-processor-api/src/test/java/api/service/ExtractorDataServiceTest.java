package api.service;

import api.converter.AnnouncementDtoEntityConverter;
import api.dto.AnnouncementDto;
import api.entity.Announcement;
import api.repository.AnnouncementRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

public class ExtractorDataServiceTest {

    private ModelMapper mapper = new ModelMapper();

    @Mock
    AnnouncementRepository announcementRepository;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mapper.addConverter(new AnnouncementDtoEntityConverter());
    }

    @Test
    public void convertAndSaveTest() {
        AnnouncementDto announcementDto = AnnouncementValues.ANNOUNCEMENT_DTO;
        Mockito.when(announcementRepository.save(Mockito.any(Announcement.class))).thenReturn(new Announcement());
        ExtractorDataService extractorDataService = new ExtractorDataService(mapper, announcementRepository);

        Announcement announcement = extractorDataService.convertAndSave(announcementDto);

        Assert.assertEquals(announcementDto.getTitle(), announcement.getTitle());
        Assert.assertEquals(announcementDto.getCreationDate(), announcement.getCreationDate());
        Assert.assertEquals(announcementDto.getImages(), announcement.getImages());
    }
}
