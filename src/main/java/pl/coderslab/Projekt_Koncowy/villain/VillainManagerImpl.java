package pl.coderslab.Projekt_Koncowy.villain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import pl.coderslab.Projekt_Koncowy.offense.Offense;
import pl.coderslab.Projekt_Koncowy.offense.OffenseLevel;
import pl.coderslab.Projekt_Koncowy.offense.OffenseRepository;
import pl.coderslab.Projekt_Koncowy.prison.Prison;
import pl.coderslab.Projekt_Koncowy.prison.PrisonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class VillainManagerImpl implements VillainManager {

    private final VillainRepository villainRepository;
    private final PrisonRepository prisonRepository;
    private final OffenseRepository offenseRepository;
    private final VillainMapper mapper;

    @Override
    public List<VillainDto> getAll() {
        List<Villain> villains = villainRepository.findAll();
        return villains.stream().map(mapper::map).toList();
    }

    @Override
    public VillainDto getById(Long id) {
        Optional<Villain> villain = villainRepository.findById(id);
        return mapper.map(villain.orElseThrow(() -> new IllegalArgumentException("No villain with id: " + id)));
    }
    @Override
    public List<VillainDto> getByPrisonId(Long id) {
        if (id == null) throw new IllegalArgumentException("Id cannot be null");
        return villainRepository.findAllByPrisonId(id).stream().map(mapper::map).toList();
    }

    @Override
    public List<VillainDto> getByOffenseLevel(OffenseLevel level) {
        if (level == null) throw new IllegalArgumentException("Level cannot be null");
        return villainRepository.findAllByOffenseLevel(level).stream().map(mapper::map).toList();
    }

    @Override
    public List<VillainDto> getVillainsByDateOfConviction(String date) {
        if (date == null) throw new IllegalArgumentException("Date cannot be null");
        return villainRepository.findByDateOfConviction(date).stream().map(mapper::map).toList();
    }

    @Override
    public List<VillainDto> getByOffenseId(Long id) {
        if (id == null) throw new IllegalArgumentException("Id cannot be null");
        return villainRepository.findByOffenseId(id).stream().map(mapper::map).toList();
    }

    @Override
    public VillainDto update(UpdateVillainParams request) {
        return villainRepository
                .findById(request.id())
                .map(villain -> {
                    villain.setFirstName(request.firstName());
                    if (request.firstName() != null) {
                        villain.setFirstName(request.firstName());
                    }
                    villain.setLastName(request.lastName());
                    if (request.lastName() != null) {
                        villain.setLastName(request.lastName());
                    }
                    villain.setOriginCountry(request.originCountry());
                    if (request.originCountry() != null) {
                        villain.setOriginCountry(request.originCountry());
                    }
                    villain.setDeposit(request.deposit());
                    if (request.deposit() != null) {
                        villain.setDeposit(request.deposit());
                    }
                    villain.setDateOfConviction(request.dateOfConviction());
                    if (request.dateOfConviction() != null) {
                        villain.setDateOfConviction(request.dateOfConviction());
                    }
                    villain.setAlive(request.alive());
                    if (!request.alive()) {
                        villain.setAlive(false);
                    }
                    villain.setPrison(request.prison());
                    if (request.prison() != null) {
                        villain.setPrison(request.prison());
                    }
                    villain.setOffense(request.offense());
                    if (request.offense() != null) {
                        villain.setOffense(request.offense());
                    }
                    villain.setAlive(request.alive());
                    return villain;

                })
                .map(villainRepository::save)
                .map(mapper::map)
                .orElseThrow(() -> new IllegalArgumentException("No villain with id " + request.id()));
    }

    @Override
    public VillainDto create(CreateVillainRequest request) {
        Prison prison =
                prisonRepository
                        .findById(request.prison())
                        .orElseThrow(
                                () -> new IllegalArgumentException("No prison with id: " + request.prison()));

        if (prison.getVillainList().size() >= prison.getNumberOfCells())
            throw new IllegalArgumentException("Cannot add new villain to the selected prison. The limit has been reached");

        Offense offense =
                offenseRepository
                        .findById(request.offense())
                        .orElseThrow(
                                () -> new IllegalArgumentException("No offense with id: " + request.offense()));

        Villain villain =
                Villain.builder()
                        .firstName(request.firstName())
                        .lastName(request.lastName())
                        .originCountry(request.originCountry())
                        .deposit(request.deposit())
                        .dateOfConviction(request.dateOfConviction())
                        .alive(request.alive())
                        .prison(prison)
                        .offense(offense)
                        .build();

        villainRepository.save(villain);
        return mapper.map(villain);
    }

    public VillainDto delete(Long id) {
        Optional<Villain> optionalVillain = villainRepository.findById(id);
        if (optionalVillain.isPresent()) {
            Villain villain = optionalVillain.get();
            villainRepository.delete(villain);
        }
        return optionalVillain.map(mapper::map)
                .orElseThrow(() -> new IllegalArgumentException("No villain with id: " + id));
    }
}

