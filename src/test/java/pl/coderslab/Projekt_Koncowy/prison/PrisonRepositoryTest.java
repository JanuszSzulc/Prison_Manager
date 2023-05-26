package pl.coderslab.Projekt_Koncowy.prison;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Sql
class PrisonRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    PrisonRepository prisonRepository;

    @Test
    public void findPrisonById() {
        Prison prison = entityManager.find(Prison.class, 100L);

        this.prisonRepository.findByName(prison.getName());
        assertThat(prison).hasFieldOrPropertyWithValue("id",100L);
        assertThat(prison).isNotNull();
        assertThat(prison).isSameAs(prison);
    }

}