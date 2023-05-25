package pl.coderslab.Projekt_Koncowy.offense;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OffenseRepository extends JpaRepository<Offense, Long> {

    Optional<Offense> findById(Long id);

}
