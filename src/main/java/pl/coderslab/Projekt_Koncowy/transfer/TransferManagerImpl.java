package pl.coderslab.Projekt_Koncowy.transfer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.Projekt_Koncowy.prison.Prison;
import pl.coderslab.Projekt_Koncowy.prison.PrisonRepository;
import pl.coderslab.Projekt_Koncowy.villain.Villain;
import pl.coderslab.Projekt_Koncowy.villain.VillainRepository;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransferManagerImpl implements TransferManager {

    private final TransferRepository transferRepository;
    private final VillainRepository villainRepository;
    private final PrisonRepository prisonRepository;
    private final TransferMapper mapper;

    public List<TransferDto> getAll() {
        return transferRepository.findAll().stream().map(mapper::map).toList();
    }

    @Override
    public TransferDto getById(Long id) {
        Optional<Transfer> transfer = transferRepository.findById(id);
        return mapper.map(transfer.orElseThrow(() -> new IllegalArgumentException("No transfer with id: " + id)));
    }

    public String addTransfer(TransferVillainRequest request) {
        Villain villain = villainRepository
                .findById(request.villainId())
                .orElseThrow(
                        () -> new NoSuchElementException("No villain with id: " + request.villainId()));

        if (villain.getPrison().getId().equals(request.prisonId()))
            throw new IllegalArgumentException("Villain is already in the this prison");

        Prison newPrison = prisonRepository
                .findById(request.prisonId())
                .orElseThrow(
                        () -> new NoSuchElementException("No prison with id: " + request.prisonId()));

        Transfer transfer = Transfer.builder()
                .villainId(villain.getId())
                .destinationPrison(newPrison.getName())
                .reason(request.reason())
                .transferTime(request.transferTime())
                .villain(List.of(villain))
                .prisons(List.of(newPrison))
                .build();

        log.info("Start transfer ...");
        long delayInSeconds = transfer.getTransferTime() * 1000;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                villain.setPrison(newPrison);
                villainRepository.save(villain);
                transferRepository.save(transfer);
                log.info(String.format("Transfer %s added...", transfer.getId()));
            }
        }, delayInSeconds);
        transfer.setExecutionStatus(true);
        return String.format("Transfer accepted for implementation. " +
                "Approximate lead time is: %s seconds", transfer.getTransferTime());
    }
}
