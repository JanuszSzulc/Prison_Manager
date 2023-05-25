package pl.coderslab.Projekt_Koncowy.villain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.coderslab.Projekt_Koncowy.offense.Offense;
import pl.coderslab.Projekt_Koncowy.prison.Prison;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class VillainRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    VillainRepository villainRepository;

    @Test
    public void whenSearchVillainByPrisonThenFindOnlyFromGivenPrison() {
        Prison prison = Prison.builder().id(1L).name("prison1").numberOfCells(1).transfer(null).build();
        entityManager.persist(prison);
        Offense offense = Offense.builder().id(101L).build();
        entityManager.persist(offense);
        Villain villain = Villain.builder().id(1L).firstName("name1").prison(prison).offense(offense).build();
        entityManager.persist(villain);

        Prison prison1 = Prison.builder().id(2L).name("prison2").numberOfCells(2).villainList(List.of()).build();
        entityManager.persist(prison1);
        Offense offense1 = Offense.builder().id(202L).build();
        entityManager.persist(offense1);
        Villain villain1 = Villain.builder().firstName("name2").prison(prison1).offense(offense1).build();
        entityManager.persist(villain1);


    }

}