package pl.coderslab.Projekt_Koncowy.transfer;

public record TransferVillainRequest(
        Long id,
        Long villainId,
        Long prisonId,
        String reason,
        Boolean executionStatus,
        String transferDate
) {
}
