package api.service;

import api.entity.Announcement;
import api.entity.PriceOffer;
import api.entity.price.ConsumerPrice;
import api.entity.price.ContactPrice;
import api.entity.price.ExchangePrice;
import api.model.DetailedAnnouncementInfo;
import api.model.GeneralAnnouncementInfo;
import api.model.Money;
import api.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FrontEndDataService {
    private AnnouncementRepository announcementRepository;
    private CurrencyService currencyService;

    public FrontEndDataService(AnnouncementRepository announcementRepository, CurrencyService currencyService) {
        this.announcementRepository = announcementRepository;
        this.currencyService = currencyService;
    }

    public List<GeneralAnnouncementInfo> getAllAnnouncements(String desiredCurrency) {
        List<Announcement> all = announcementRepository.findAll();
        if (all != null && !all.isEmpty()) {
            return all.stream()
                    .map(announcement -> convertAnnouncementToGeneralFormat(announcement, desiredCurrency))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public GeneralAnnouncementInfo getGeneralInfoAnnouncement(Integer id, String desiredCurrency) {
        Optional<Announcement> ann = announcementRepository.findById(id);
        return ann.map(announcement -> convertAnnouncementToGeneralFormat(announcement, desiredCurrency))
                .orElse(null);
    }

    public DetailedAnnouncementInfo getDetailedInfoAnnouncement(Integer id, String desiredCurrency) {
        return null;
    }

    private GeneralAnnouncementInfo convertAnnouncementToGeneralFormat(Announcement announcement, String desiredCurrency) {

        String cost = getBaseCostText(announcement, desiredCurrency, currencyService);

        GeneralAnnouncementInfo generalAnnouncementInfo = new GeneralAnnouncementInfo();
        generalAnnouncementInfo.setBaseCost(cost);
        generalAnnouncementInfo.setCreationDate(announcement.getCreationDate().toString());
        generalAnnouncementInfo.setLessorType(announcement.getLessor().getLessorType());
        generalAnnouncementInfo.setLocation(announcement.getLocation().getFullLocation());
        generalAnnouncementInfo.setProvider(announcement.getProvider());
        generalAnnouncementInfo.setTitle(announcement.getTitle());
        generalAnnouncementInfo.setUrl(announcement.getUrl());
        return generalAnnouncementInfo;
    }

    private String getBaseCostText(Announcement announcement, String desiredCurrency, CurrencyService currencyService) {
        PriceOffer priceOffer = announcement.getPriceOffer();

        if (priceOffer instanceof ConsumerPrice) {
            ConsumerPrice consumerPrice = (ConsumerPrice) priceOffer;
            Money cost = new Money(currencyService, consumerPrice.getPrice(), announcement.getCurrency());
            cost.recalculateToCurrency(desiredCurrency);
            return cost.getPriceWithCurrency();
        } else if (priceOffer instanceof ContactPrice) {
            System.out.println("contact price");
            return priceOffer.getDescription();
        } else if (priceOffer instanceof ExchangePrice) {
            System.out.println("exchange");
            return priceOffer.getDescription();
        }
        return null;
    }
}
