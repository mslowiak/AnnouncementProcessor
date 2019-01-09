package api.repository;

import api.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    Optional<Currency> findCurrencyByCurrencyFromAndCurrencyTo(String from, String to);
}
