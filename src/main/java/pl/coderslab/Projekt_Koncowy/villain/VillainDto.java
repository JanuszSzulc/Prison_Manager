package pl.coderslab.Projekt_Koncowy.villain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;


public record VillainDto(Long id,
                         String firstName,
                         String lastName,
                         Long prison,
                         String prisonName,
                         String originCountry,
                         Double deposit,
                         String getDateOfConviction,
                         Boolean alive,
                         @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss") LocalDateTime createdOn,
                         Long offense,
                         Enum level,
                         String offenseDescription) {

}
