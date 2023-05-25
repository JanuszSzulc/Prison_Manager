package pl.coderslab.Projekt_Koncowy.transfer;

import org.springframework.stereotype.Component;
import pl.coderslab.Projekt_Koncowy.villain.Villain;

import java.util.stream.Collectors;

@Component
public class DefaultTransferMapper implements TransferMapper {

    @Override
    public TransferDto map(Transfer transfer) {
        return new TransferDto(
                transfer.getId(),
                transfer.getDestinationPrison(),
                transfer.getReason(),
                transfer.isExecutionStatus(),
                transfer.getTransferDate()
        );
    }
}
