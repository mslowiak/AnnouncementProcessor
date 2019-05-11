package api.repository;

import api.dto.SearchCriteria;
import api.entity.Announcement;
import api.model.GeneralAnnouncementInfo;
import lombok.AllArgsConstructor;
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
                announcementsResultRoot.get("priceOffer").get("price"),
                announcementsResultRoot.get("description"),
                announcementsResultRoot.get("provider"),
                announcementsResultRoot.get("creationDate"),
                announcementsResultRoot.get("url"),
                announcementsResultRoot.get("lessor").get("lessorType"),
                announcementsResultRoot.get("location").get("city")
        ));

        TypedQuery<GeneralAnnouncementInfo> resultQuery = entityManager.createQuery(criteria);
        resultQuery.setFirstResult((page.getPageNumber() - 1) * page.getPageSize());
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

        if (searchCriteria.getLessor() != null) {
            predicates.add(cb.and(root.get("lessor").get("lessorType").in(searchCriteria.getLessor())));
        }
        return predicates.toArray(new Predicate[0]);
    }
}
