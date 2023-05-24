package pl.coderslab.Projekt_Koncowy.prison;

public record PrisonDto(Long id,
                        String name,
                        String dateOpened,
                        Integer numberOfCells) {
}
