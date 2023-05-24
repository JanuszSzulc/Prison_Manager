package pl.coderslab.Projekt_Koncowy.offense;

import org.springframework.stereotype.Component;

@Component
public class DefaultOffenseMapper implements OffenseMapper {

    @Override
    public OffenseDto map(Offense offense) {
        return new OffenseDto(offense.getId(),
                offense.getLevel(),
                offense.getDescription()
        );
    }
}
