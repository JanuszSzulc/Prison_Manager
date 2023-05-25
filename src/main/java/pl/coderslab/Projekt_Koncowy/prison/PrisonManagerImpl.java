package pl.coderslab.Projekt_Koncowy.prison;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrisonManagerImpl implements PrisonManager {

    private final PrisonRepository prisonRepository;
    private final PrisonMapper mapper;

    @Override
    public List<PrisonDto> getAll() {
        List<Prison> prisons = prisonRepository.findAll();
        return prisons.stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public Optional<PrisonDto> getById(Long id) {
        Optional<Prison> prison = prisonRepository.findById(id);
        return Optional.ofNullable(mapper.map(prison.orElseThrow(() -> new IllegalArgumentException("No prison with id: " + id))));
    }

    @Override
    public Optional<PrisonDto> findByName(String name) {
        Optional<Prison> prison = prisonRepository.findByName(name);
        return Optional.ofNullable(prison.map(mapper::map)
                .orElseThrow(() -> new IllegalArgumentException("No prison with name: " + name)));
    }

    @Override
    public PrisonDto update(UpdatePrisonParams request) {
        return prisonRepository
                .findById(request.id())
                .map(prison -> {
                    prison.setName(request.name());
                    if (request.name() != null) {
                        if (prisonRepository.findByName(prison.getName()).isPresent()) {
                            throw new IllegalArgumentException("Prison with this name already exist");
                        }
                        prison.setName(request.name());
                    }
                    prison.setDateOpened(request.dateOpened());
                    if (request.dateOpened() != null) {
                        if (!prison.getDateOpened().matches("^([0][1-9]|[1-2][0-9]|[3][0-1])\\.([0][1-9]|[1][0,1,2])\\.[1-9]{1}[0-9]{3}$")) {
                            throw new ValidationException("Date opened date must be in format dd.MM.yyyy");
                        }
                        prison.setDateOpened(request.dateOpened());
                    }
                    prison.setNumberOfCells(request.numberOfCells());
                    if (request.numberOfCells() != null) {
                        prison.setNumberOfCells(request.numberOfCells());
                    }
                    return prison;
                })
                .map(prisonRepository::save)
                .map(mapper::map)
                .orElseThrow(() -> new IllegalArgumentException("No prison with id " + request.id()));
    }

    @Override
    public PrisonDto create(CreatePrisonRequest request) {
        Prison prison =
                Prison.builder()
                        .name(request.name())
                        .dateOpened(request.dateOpened())
                        .numberOfCells(request.numberOfCells())
                        .build();
        if (prisonRepository.findByName(prison.getName()).isPresent()) {
            throw new IllegalArgumentException("Prison with this name already exist");
        } else {
            prisonRepository.save(prison);
            return mapper.map(prison);
        }
    }

    public PrisonDto delete(Long id) {
        Optional<Prison> optionalPrison = prisonRepository.findById(id);
        if (optionalPrison.isPresent()) {
            Prison prison = optionalPrison.get();
            prisonRepository.delete(prison);
        }
        return optionalPrison.map(mapper::map)
                .orElseThrow(() -> new IllegalArgumentException("No prison with id: " + id));
    }
}