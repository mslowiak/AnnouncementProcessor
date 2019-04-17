package api.repository;

import api.model.GeneralAnnouncementInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class AnnouncementRepositoryImpl implements AnnouncementRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<GeneralAnnouncementInfo> getListOfAnnouncements() {
        String selectColumns = "an.id as id, " +
                "an.title as title, " +
                "po.real_price as baseCost, " +
                "LEFT(an.description, 100) as description, " +
                "an.provider as provider, " +
                "an.creation_date as creationDate, " +
                "an.url as url, " +
                "l.lessor_type as lessorType, " +
                "concat(loc.city, ' ', loc.district) as location";
        Query nativeQuery = em.createNativeQuery(
                "SELECT " + selectColumns + " FROM announcements as an " +
                        "LEFT OUTER JOIN (SELECT id, real_price FROM price_offer) as po " +
                        "ON an.price_id = po.id " +
                        "LEFT OUTER JOIN (SELECT id, lessor_type FROM lessors) as l " +
                        "ON an.id = l.id " +
                        "LEFT OUTER JOIN (SELECT id, city, district FROM locations) as loc " +
                        "ON an.id = loc.id",
                "GeneralAnnouncementInfoMapping");

        return (List<GeneralAnnouncementInfo>) nativeQuery.getResultList();
    }
}
