package pl.coderslab.Projekt_Koncowy.transfer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.Projekt_Koncowy.utils.ControllerUtil;
import pl.coderslab.Projekt_Koncowy.utils.Wrapper;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/transfers")
@Slf4j
@RequiredArgsConstructor
public class TransferController {

    private final TransferManagerImpl transferManager;

    @GetMapping
    public ResponseEntity<Wrapper<List<TransferDto>>> getTransfers(){
        return ControllerUtil.handle(() -> transferManager.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Wrapper<Void>> createTransfer(@RequestBody @Valid TransferVillainRequest request){
        return ControllerUtil.handle(() -> transferManager.addTransfer(request));
    }

}
