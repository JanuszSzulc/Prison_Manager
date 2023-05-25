package pl.coderslab.Projekt_Koncowy.transfer;

import java.util.List;

public interface TransferManager {

    List<TransferDto> getAll();
    TransferDto getById(Long id);
    String addTransfer(TransferVillainRequest request);
}
