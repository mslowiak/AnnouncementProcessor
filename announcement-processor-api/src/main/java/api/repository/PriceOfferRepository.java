package api.repository;

import api.entity.PriceOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceOfferRepository extends JpaRepository<PriceOffer, Integer> {
}
