package pl.coderslab.Projekt_Koncowy.offense;

import java.util.List;
import java.util.Optional;

public interface OffenseManager {
    List<OffenseDto> getAll();

    Optional<OffenseDto> getById(Long id);

    OffenseDto update(UpdateOffenseParams request);

    OffenseDto create(CreateOffenseRequest request);

    OffenseDto delete(Long id);
}
