package pl.coderslab.Projekt_Koncowy.offense;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Projekt_Koncowy.utils.ControllerUtil;
import pl.coderslab.Projekt_Koncowy.utils.Wrapper;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/offense")
@Slf4j
@RequiredArgsConstructor
public class OffenseController {
    private final OffenseManager offenseManager;

    @GetMapping
    public ResponseEntity<Wrapper<List<OffenseDto>>> getAllOffenses() {
        return ControllerUtil.handle(() -> offenseManager.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wrapper<OffenseDto>> getOffense(@PathVariable Long id) {
        return ControllerUtil.handle(() -> offenseManager.getById(id).orElseThrow());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wrapper<OffenseDto>> updateVillain(@RequestBody @Valid UpdateOffenseParams request) {
        return ControllerUtil.handle(() -> offenseManager.update(request));
    }

    @PostMapping
    public ResponseEntity<Wrapper<OffenseDto>> createOffense(@RequestBody @Valid CreateOffenseRequest request) {
        return ControllerUtil.handle(() -> offenseManager.create(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Wrapper<OffenseDto>> delete(@PathVariable Long id) {
        return ControllerUtil.handle(() -> offenseManager.delete(id));
    }
}
