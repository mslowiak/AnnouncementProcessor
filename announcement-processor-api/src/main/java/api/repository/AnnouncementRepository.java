package api.repository;

import api.entity.Announcement;
import api.model.GeneralAnnouncementInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
    @Query("SELECT NEW api.model.GeneralAnnouncementInfo(" +
            "an.id, an.title, an.images, po.price, SUBSTRING(an.description, 1, 100), an.provider, an.creationDate, an.url, l.lessorType, concat(loc.city, ' ', loc.district)) " +
            "FROM Announcement as an " +
            "LEFT JOIN an.priceOffer as po " +
            "LEFT JOIN an.lessor as l " +
            "LEFT JOIN an.location as loc")
    Page<GeneralAnnouncementInfo> getGeneralAnnouncementsInfo(Pageable pageable);
}
