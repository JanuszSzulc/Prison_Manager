package pl.coderslab.Projekt_Koncowy.prison;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Projekt_Koncowy.utils.ControllerUtil;
import pl.coderslab.Projekt_Koncowy.utils.Wrapper;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/prisons")
@Slf4j
@RequiredArgsConstructor
public class PrisonController {

    private final PrisonManager prisonManager;

    @GetMapping
    public ResponseEntity<Wrapper<List<PrisonDto>>> getAll() {
        return ControllerUtil.handle(() -> prisonManager.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wrapper<PrisonDto>> getPrison(@PathVariable Long id) {
        return ControllerUtil.handle(() -> prisonManager.getById(id).orElseThrow());
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Wrapper<PrisonDto>> getPrisonByName(@PathVariable String name) {
        return ControllerUtil.handle(() -> prisonManager.findByName(name).orElseThrow());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wrapper<PrisonDto>> updatePrison(@RequestBody @Valid UpdatePrisonParams request) {
        return ControllerUtil.handle(() -> prisonManager.update(request));
    }

    @PostMapping
    public ResponseEntity<Wrapper<PrisonDto>> createPrison(@RequestBody @Valid CreatePrisonRequest request) {
        return ControllerUtil.handle(() -> prisonManager.create(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Wrapper<PrisonDto>> deletePrison(@PathVariable Long id) {
        return ControllerUtil.handle(() -> prisonManager.delete(id));
    }
}
