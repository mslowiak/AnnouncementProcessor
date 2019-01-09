package api.repository;

import api.entity.AdditionalCosts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalCostsRepository extends JpaRepository<AdditionalCosts, Integer> {
}
