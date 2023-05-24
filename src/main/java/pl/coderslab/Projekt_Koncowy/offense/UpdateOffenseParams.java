package pl.coderslab.Projekt_Koncowy.offense;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

public record UpdateOffenseParams(
        Long id,
        @Enumerated
        OffenseLevel level,
        @NotBlank
        String description
) {
}
