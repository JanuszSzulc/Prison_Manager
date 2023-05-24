package pl.coderslab.Projekt_Koncowy.prison;

import javax.validation.constraints.NotBlank;

public record UpdatePrisonParams(
        Long id,
        @NotBlank
        String name,
        String dateOpened,
        Integer numberOfCells
) {
}
