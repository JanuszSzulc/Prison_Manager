package pl.coderslab.Projekt_Koncowy.villain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.Projekt_Koncowy.offense.OffenseLevel;

import java.util.List;
import java.util.Optional;

public interface VillainRepository extends JpaRepository<Villain, Long> {

    Optional<Villain> findById(Long id);

    List<Villain> findAllByPrisonId(Long prisonId);

    List<Villain> findAllByOffenseLevel(OffenseLevel level);

//    @Query(value = "select v from Villain v where v.dateOfConviction >= :date")
    List<Villain> findByDateOfConvictionGreaterThanEqual(String date);

    List<Villain> findByOffenseId(Long id);
}
