package pl.coderslab.Projekt_Koncowy.transfer;

import org.springframework.stereotype.Component;

@Component
public class DefaultTransferMapper implements TransferMapper {

    @Override
    public TransferDto map(Transfer transfer) {
        return new TransferDto(
                transfer.getId(),
                transfer.getVillainId(),
                transfer.getDestinationPrison(),
                transfer.getReason(),
                transfer.isExecutionStatus(),
                transfer.getTransferTime()
        );
    }
}
