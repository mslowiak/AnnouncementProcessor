package api.repository;

import api.dto.SearchCriteria;
import api.entity.Announcement;
import api.model.GeneralAnnouncementInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Repository
public class SearchRepository {
    private EntityManager entityManager;

    public Page<GeneralAnnouncementInfo> search(SearchCriteria searchCriteria, Pageable page) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<GeneralAnnouncementInfo> criteria = cb.createQuery(GeneralAnnouncementInfo.class);
        Root<Announcement> announcementsResultRoot = criteria.from(Announcement.class);

        announcementsResultRoot.join("priceOffer");
        announcementsResultRoot.join("lessor");
        announcementsResultRoot.join("location");
        announcementsResultRoot.join("propertyData");

        Predicate[] filterPredicates = createFilterPredicates(cb, announcementsResultRoot, searchCriteria);

        criteria.where(cb.and(filterPredicates));
        criteria.select(cb.construct(GeneralAnnouncementInfo.class,
                announcementsResultRoot.get("id"),
                announcementsResultRoot.get("title"),
                announcementsResultRoot.get("images"),
                announcementsResultRoot.get("priceOffer").get("price"),
                announcementsResultRoot.get("description"),
                announcementsResultRoot.get("provider"),
                announcementsResultRoot.get("creationDate"),
                announcementsResultRoot.get("url"),
                announcementsResultRoot.get("lessor").get("lessorType"),
                announcementsResultRoot.get("location").get("city")
        ));

        TypedQuery<GeneralAnnouncementInfo> resultQuery = entityManager.createQuery(criteria);
        resultQuery.setFirstResult(page.getPageNumber() * page.getPageSize());
        resultQuery.setMaxResults(page.getPageSize());

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Announcement> announcementCountAllRoot = countQuery.from(Announcement.class);
        countQuery.select(cb.count(announcementCountAllRoot)).where(cb.and(filterPredicates));
        TypedQuery<Long> totalRowsQuery = entityManager.createQuery(countQuery);

        return new PageImpl<>(resultQuery.getResultList(), page, totalRowsQuery.getSingleResult());
    }

    private Predicate[] createFilterPredicates(CriteriaBuilder cb,
                                               Root<Announcement> root,
                                               SearchCriteria searchCriteria) {
        List<Predicate> predicates = new ArrayList<>();

        List<String> lessor = searchCriteria.getLessor();
        if (lessor != null) {
            System.out.println(lessor);
            predicates.add(cb.and(root.get("lessor").get("lessorType").in(lessor)));
        }

        List<String> rooms = searchCriteria.getRooms();
        if (rooms != null) {
            predicates.add(cb.and(root.get("propertyData").get("roomNumber").in(rooms)));
        }

        List<String> baths = searchCriteria.getBaths();
        if (baths != null) {
            predicates.add(cb.and(root.get("propertyData").get("bathroomNumber").in(baths)));
        }

        List<String> parking = searchCriteria.getParking();
        if (baths != null) {
            predicates.add(cb.and(root.get("propertyData").get("parkingAvailability").in(parking)));
        }

        List<String> smokers = searchCriteria.getSmokers();
        if (baths != null) {
            predicates.add(cb.and(root.get("propertyData").get("isSmokingAllowed").in(smokers)));
        }

        List<String> pets = searchCriteria.getPets();
        if (baths != null) {
            predicates.add(cb.and(root.get("propertyData").get("isPetFriendly").in(pets)));
        }

        Integer priceFrom = searchCriteria.getPriceFrom();
        Integer priceTo = searchCriteria.getPriceTo();
        if (priceFrom != null && priceTo != null) {
            predicates.add(cb.between(root.get("priceOffer").get("price"), priceFrom, priceTo));
        }

        Integer areaFrom = searchCriteria.getAreaFrom();
        Integer areaTo = searchCriteria.getAreaTo();
        if (areaFrom != null && areaTo != null) {
            predicates.add(cb.between(root.get("propertyData").get("area"), areaFrom, areaTo));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
