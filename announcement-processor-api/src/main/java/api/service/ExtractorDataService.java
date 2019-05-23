package api.service;

import api.dto.AnnouncementDto;
import api.entity.Announcement;
import api.repository.AnnouncementRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExtractorDataService {

    private final ModelMapper mapper;
    private final AnnouncementRepository announcementRepository;

    @Autowired
    public ExtractorDataService(ModelMapper mapper, AnnouncementRepository announcementRepository) {
        this.mapper = mapper;
        this.announcementRepository = announcementRepository;
    }


    public Announcement convertAndSave(AnnouncementDto announcementDto) {
        Announcement announcement = announcementDtoToEntity(announcementDto);
        announcementRepository.save(announcement);
        return announcement;
    }

    private Announcement announcementDtoToEntity(AnnouncementDto announcementDto) {
        log.info("Mapping announcementDto to announcement...");
        Announcement announcement = mapper.map(announcementDto, Announcement.class);
        System.out.println("\n\nXDDDDDDDDDD");
        System.out.println(announcementDto.getImages());
        System.out.println(announcement.getImages());
        System.out.println("\n\n");
        log.info("Mapped announcementDto to announcement. AnnouncementTitle: {}", announcement.getTitle());
        return announcement;
    }
}
