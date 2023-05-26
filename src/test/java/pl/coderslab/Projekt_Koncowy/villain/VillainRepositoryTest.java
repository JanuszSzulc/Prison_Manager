package pl.coderslab.Projekt_Koncowy.villain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import pl.coderslab.Projekt_Koncowy.offense.Offense;
import pl.coderslab.Projekt_Koncowy.prison.Prison;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@Sql
class VillainRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    VillainRepository villainRepository;

    @Test
    public void whenSearchVillainByPrisonThenFindOnlyFromGivenPrison() {
        Prison prison = entityManager.find(Prison.class, 100L);

        List<Villain> villainList = this.villainRepository.findAllByPrisonId(prison.getId());
        assertThat(villainList).hasSize(2);
        assertThat(villainList.get(0)).isNotNull();
        assertThat(villainList.get(0)).hasFieldOrPropertyWithValue("id", 100L);
    }

    @Test
    public void whenSearchForVillainByOffenseLevelThenFindOnlyFromGivenLevel() {
        Offense offense = entityManager.find(Offense.class, 120L);

        List<Villain> villainList = this.villainRepository.findAllByOffenseLevel(offense.getLevel());
        assertThat(villainList).hasSize(1);
        assertThat(villainList.get(0)).isNotNull();
        assertThat(villainList.get(0)).hasFieldOrPropertyWithValue("id", 300L);
    }

    @Test
    public void whenSearchForVillainByOffenseIdThenFindOnlyFromGivenId() {
        Offense offense = entityManager.find(Offense.class, 110L);

        List<Villain> villainList = this.villainRepository.findByOffenseId(offense.getId());
        assertThat(villainList).hasSize(2);
        assertThat(villainList.get(0)).isNotNull();
        assertThat(villainList.get(0)).hasFieldOrPropertyWithValue("id", 100L);
        assertThat(villainList.get(1)).hasFieldOrPropertyWithValue("id", 200L);
    }
}