package pl.coderslab.Projekt_Koncowy.offense;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static pl.coderslab.Projekt_Koncowy.offense.OffenseLevel.LOW;

@DataJpaTest
@Sql
class OffenseRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    OffenseRepository offenseRepository;

    @Test
    public void whenSearchOffenseById_thenFindOnlyWithGivenId() {
        Offense offense = entityManager.find(Offense.class, 130L);

        Optional<Offense> offenseList = this.offenseRepository.findById(offense.getId());
        assertThat(offenseList).isNotEmpty();
        assertThat(offenseList.get()).isNotNull();
        assertThat(offenseList.get()).hasFieldOrPropertyWithValue("id", 130L);
        assertThat(offenseList.get()).hasFieldOrPropertyWithValue("level", LOW);
        assertThat(offenseList.get()).hasFieldOrPropertyWithValue("description", "exceeding the speed limit");
    }
}