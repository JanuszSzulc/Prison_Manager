package pl.coderslab.Projekt_Koncowy.villain;

import org.springframework.stereotype.Component;

@Component
public class DefaultVillainMapper implements VillainMapper {

    @Override
    public VillainDto map(Villain villain) {
        return new VillainDto(villain.getId(),
                villain.getFirstName(),
                villain.getLastName(),
                villain.getPrison().getId(),
                villain.getPrison().getName(),
                villain.getOriginCountry(),
                villain.getDeposit(),
                villain.getDateOfConviction(),
                villain.isAlive(),
                villain.getCreatedOn(),
                villain.getOffense().getId(),
                villain.getOffense().getLevel(),
                villain.getOffense().getDescription()
        );
    }
}
