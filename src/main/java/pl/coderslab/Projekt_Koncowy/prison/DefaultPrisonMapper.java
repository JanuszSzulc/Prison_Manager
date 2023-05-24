package pl.coderslab.Projekt_Koncowy.prison;

import org.springframework.stereotype.Component;

@Component
public class DefaultPrisonMapper implements PrisonMapper {
    @Override
    public PrisonDto map(Prison prison) {
        return new PrisonDto(prison.getId(),
                prison.getName(),
                prison.getDateOpened(),
                prison.getNumberOfCells());
    }
}
