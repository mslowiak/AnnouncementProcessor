package parser.registry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParsingInfoRepository extends JpaRepository<ParsingInfo, Integer> {
}
