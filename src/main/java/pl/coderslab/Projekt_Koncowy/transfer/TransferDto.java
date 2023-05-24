package pl.coderslab.Projekt_Koncowy.transfer;

public record TransferDto(Long id,
                          String destinationPrison,
                          String reason,
                          boolean executionStatus,
                          String transferDate) {
}
