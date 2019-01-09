package parser.registry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ParsingInfoRepository extends JpaRepository<ParsingInfo, Integer> {
    Optional<ParsingInfo> findFirstByProviderOrderByDateDescCounterPerDateDesc(String provider);
    Optional<ParsingInfo> findFirstByProviderAndDateEqualsAndCounterPerDateLessThanOrderByCounterPerDateDesc(String provider, LocalDate date, Integer counterPerDate);
    Optional<ParsingInfo> findFirstByProviderAndDateLessThanOrderByDateDescCounterPerDateDesc(String provider, LocalDate date);
}
