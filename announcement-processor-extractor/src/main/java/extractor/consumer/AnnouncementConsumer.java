package extractor.consumer;

import extractor.dto.AnnouncementDto;

import java.util.Optional;


public interface AnnouncementConsumer {

    Optional<AnnouncementDto> consumeAnnouncement();
}
