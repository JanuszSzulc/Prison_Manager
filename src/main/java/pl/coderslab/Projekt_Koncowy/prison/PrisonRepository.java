package pl.coderslab.Projekt_Koncowy.prison;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrisonRepository extends JpaRepository<Prison, Long> {
    Optional<Prison> findById(Long id);

    Optional<Prison> findByName(String name);
}
