package pl.coderslab.Projekt_Koncowy.offense;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OffenseManagerImpl implements OffenseManager {

    private final OffenseRepository offenseRepository;
    private final OffenseMapper mapper;

    @Override
    public List<OffenseDto> getAll() {
        List<Offense> offense = offenseRepository.findAll();
        return offense.stream().map(mapper::map).toList();
    }

    @Override
    public Optional<OffenseDto> getById(Long id) {
        Optional<Offense> offense = offenseRepository.findById(id);
        return Optional.ofNullable(mapper.map(offense.orElseThrow(() -> new IllegalArgumentException("No offense with id: " + id))));
    }

    @Override
    public OffenseDto update(UpdateOffenseParams request) {
        return offenseRepository
                .findById(request.id())
                .map(offense -> {
                    offense.setLevel(request.level());
                    if (request.level() != null) {
                        offense.setLevel(request.level());
                    }
                    offense.setDescription(request.description());
                    if (request.description() != null) {
                        offense.setDescription(request.description());
                    }
                    return offense;
                })
                .map(offenseRepository::save)
                .map(mapper::map)
                .orElseThrow(() -> new IllegalArgumentException("No offense with id: " + request.id()));
    }

    @Override
    public OffenseDto create(CreateOffenseRequest request) {
        Offense offense =
                Offense.builder()
                        .level(request.level())
                        .description(request.description())
                        .build();
        offenseRepository.save(offense);
        return mapper.map(offense);
    }

    @Override
    public OffenseDto delete(Long id) {
        Optional<Offense> optionalOffense = offenseRepository.findById(id);
        if (optionalOffense.isPresent()) {
            Offense offense = optionalOffense.get();
            offenseRepository.delete(offense);

        }
        return optionalOffense.map(mapper::map)
                .orElseThrow(() -> new IllegalArgumentException("No offense with id: " + id));
    }
}
