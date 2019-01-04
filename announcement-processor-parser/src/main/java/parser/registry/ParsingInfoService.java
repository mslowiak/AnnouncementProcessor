package parser.registry;

import org.springframework.stereotype.Service;
import parser.Announcement;

import java.util.Optional;

@Service
public class ParsingInfoService {

    private ParsingInfoRepository parsingInfoRepository;

    public ParsingInfoService(ParsingInfoRepository parsingInfoRepository) {
        this.parsingInfoRepository = parsingInfoRepository;
    }

    public Optional<ParsingInfo> fetchLastRecordForProvider(String provider, ParsingInfo lastParsedAnnouncement) {
        Optional<ParsingInfo> announcement;
        if (lastParsedAnnouncement == null) {
            announcement = parsingInfoRepository.findFirstByProviderOrderByDateDescCounterPerDateDesc(provider);
        } else {
            announcement = parsingInfoRepository.findFirstByProviderAndDateEqualsAndCounterPerDateLessThanOrderByCounterPerDateDesc(
                    provider,
                    lastParsedAnnouncement.getDate(),
                    lastParsedAnnouncement.getCounterPerDate()
            );
            if (!announcement.isPresent()) {
                announcement = parsingInfoRepository.findFirstByProviderAndDateLessThanOrderByDateDescCounterPerDateDesc(
                        provider,
                        lastParsedAnnouncement.getDate()
                );
            }
        }
        return announcement;
    }

    public void saveAnnouncementToRegistry(String provider, Announcement announcement, int counterNumber) {
        ParsingInfo parsingInfo = new ParsingInfo();
        parsingInfo.setCounterPerDate(counterNumber);
        parsingInfo.setDate(announcement.getCreationDate().toLocalDate());
        parsingInfo.setProvider(provider);
        parsingInfo.setUrl(announcement.getUrl());
        parsingInfo.setPageHash("" + announcement.hashCode());

        parsingInfoRepository.save(parsingInfo);
    }
}
