package extractor.consumer;

import extractor.dto.AnnouncementDto;

import java.io.IOException;

public interface AnnouncementConsumer {

    AnnouncementDto consumeAnnouncement() throws IOException;
}
