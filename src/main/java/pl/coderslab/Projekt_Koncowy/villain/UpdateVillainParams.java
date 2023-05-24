package pl.coderslab.Projekt_Koncowy.villain;

import pl.coderslab.Projekt_Koncowy.offense.Offense;
import pl.coderslab.Projekt_Koncowy.prison.Prison;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UpdateVillainParams(
        Long id,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        String originCountry,
        Double deposit,
        @NotBlank
        String dateOfConviction,
        @NotNull
        Boolean alive,
        Prison prison,
        Offense offense) {
}
