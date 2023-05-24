package pl.coderslab.Projekt_Koncowy.prison;

import java.util.List;
import java.util.Optional;

public interface PrisonManager {

    List<PrisonDto> getAll();

    Optional<PrisonDto> getById(Long id);

    Optional<PrisonDto> findByName(String name);

    PrisonDto create(CreatePrisonRequest request);

    PrisonDto delete(Long id);

    PrisonDto update(UpdatePrisonParams request);
}
