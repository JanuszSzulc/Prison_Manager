package pl.coderslab.Projekt_Koncowy.villain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Projekt_Koncowy.offense.OffenseDto;
import pl.coderslab.Projekt_Koncowy.offense.OffenseLevel;
import pl.coderslab.Projekt_Koncowy.offense.OffenseManager;
import pl.coderslab.Projekt_Koncowy.prison.PrisonDto;
import pl.coderslab.Projekt_Koncowy.prison.PrisonManager;
import pl.coderslab.Projekt_Koncowy.utils.ControllerUtil;
import pl.coderslab.Projekt_Koncowy.utils.Wrapper;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/villains")
@Slf4j
@RequiredArgsConstructor
public class VillainController {

    private final VillainManager villainManager;
    private final PrisonManager prisonManager;
    private final OffenseManager offenseManager;

    @GetMapping
    public ResponseEntity<Wrapper<List<VillainDto>>> getAllVillains() {
        return ControllerUtil.handle(() -> villainManager.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wrapper<VillainDto>> getVillain(@PathVariable Long id) {
        return ControllerUtil.handle(() -> villainManager.getById(id));
    }

    @GetMapping("/offense/{level}")
    public ResponseEntity<Wrapper<List<VillainDto>>> findVillainsByOffense(@PathVariable OffenseLevel level) {
        return ControllerUtil.handle(() -> villainManager.getByOffenseLevel(level));
    }

    @GetMapping("/allVillainsByDateOfConviction/{date}")
    public ResponseEntity<Wrapper<List<VillainDto>>> findAllByDateOfConviction(@PathVariable String date) {
        return ControllerUtil.handle(() -> villainManager.getVillainsByDateOfConviction(date));
    }

    @GetMapping("/allVillainsByOffense/{id}")
    public ResponseEntity<Wrapper<List<VillainDto>>> findAllByOffenseId(@PathVariable Long id) {
        return ControllerUtil.handle(() -> {
            OffenseDto offense = offenseManager.getById(id).orElseThrow(() -> new NoSuchElementException("No offense with id: " + id));
            return villainManager.getByOffenseId(offense.id());
        });
    }

    @GetMapping("/getVillainsByPrisonId/{id}")
    public ResponseEntity<Wrapper<List<VillainDto>>> findVillainsByPrison(@PathVariable Long id) {
        return ControllerUtil.handle(() -> {
            PrisonDto prison = prisonManager.getById(id).orElseThrow(() -> new NoSuchElementException("No prison with id: " + id));
            return villainManager.getByPrisonId(prison.id());
        });
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wrapper<VillainDto>> updateVillain(@RequestBody @Valid UpdateVillainParams request) {
        return ControllerUtil.handle(() -> villainManager.update(request));
    }

    @PostMapping
    public ResponseEntity<Wrapper<VillainDto>> createVillain(@RequestBody @Valid CreateVillainRequest request) {
        return ControllerUtil.handle(() -> villainManager.create(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Wrapper<VillainDto>> delete(@PathVariable Long id) {
        return ControllerUtil.handle(() -> villainManager.delete(id));
    }
}