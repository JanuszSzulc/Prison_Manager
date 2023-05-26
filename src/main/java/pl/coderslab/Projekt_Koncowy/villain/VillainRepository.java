package pl.coderslab.Projekt_Koncowy.villain;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.Projekt_Koncowy.offense.OffenseLevel;

import java.util.List;
import java.util.Optional;

public interface VillainRepository extends JpaRepository<Villain, Long> {

    Optional<Villain> findById(Long id);

    List<Villain> findAllByPrisonId(Long prisonId);

    List<Villain> findAllByOffenseLevel(OffenseLevel level);

    List<Villain> findByDateOfConvictionGreaterThanEqual(String date);

    List<Villain> findByOffenseId(Long id);
}
