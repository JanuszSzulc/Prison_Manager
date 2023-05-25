package pl.coderslab.Projekt_Koncowy.transfer;

public record TransferDto(Long id,
                          Long villainId,
                          String destinationPrison,
                          String reason,
                          boolean executionStatus,
                          long transferTime) {
}
