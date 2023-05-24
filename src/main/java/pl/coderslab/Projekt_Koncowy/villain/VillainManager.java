package pl.coderslab.Projekt_Koncowy.villain;

import pl.coderslab.Projekt_Koncowy.offense.OffenseLevel;

import java.util.List;

public interface VillainManager {

    List<VillainDto> getAll();

    VillainDto getById(Long id);

    VillainDto update(UpdateVillainParams updateVillainParams);

    VillainDto create(CreateVillainRequest request);

    VillainDto delete(Long id);

    List<VillainDto> getByPrisonId(Long id);

    List<VillainDto> getByOffenseLevel(OffenseLevel level);

    List<VillainDto> getVillainsByDateOfConviction(String date);

    List<VillainDto> getByOffenseId(Long id);
}
