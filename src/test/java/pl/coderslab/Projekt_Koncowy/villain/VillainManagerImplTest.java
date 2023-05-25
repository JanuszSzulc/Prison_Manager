package pl.coderslab.Projekt_Koncowy.villain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.coderslab.Projekt_Koncowy.offense.Offense;
import pl.coderslab.Projekt_Koncowy.offense.OffenseRepository;
import pl.coderslab.Projekt_Koncowy.prison.Prison;
import pl.coderslab.Projekt_Koncowy.prison.PrisonRepository;
import pl.coderslab.Projekt_Koncowy.transfer.TransferRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class VillainManagerImplTest {

    @Mock
    VillainRepository villainRepository;

    @Mock
    PrisonRepository prisonRepository;

    @Mock
    TransferRepository transferRepository;

    @Mock
    OffenseRepository offenseRepository;

    @Mock
    DefaultVillainMapper defaultVillainMapper;

    @InjectMocks
    VillainManagerImpl villainService;


    @Test
    public void getVillainWithNonExistingId(){
        Mockito.when(villainRepository.findById(100L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> villainService.getById(100L)
        );
        assertEquals("No villain with id: 100", exception.getMessage());
    }

    @Test
    public void createVillainWithNonExistingPrison(){
        RuntimeException exception = assertThrows(RuntimeException.class,
                () ->
                        villainService.create(
                                new CreateVillainRequest(1L,"name", "lastName", "country",
                                        2L, "01.01.2022", 100.55, true,
                                        101L, "robbery")));

        assertEquals("No prison with id: 2", exception.getMessage());
    }

    @Test
    public void createVillainWithNonExistingOffence() {
        Prison prison = Prison.builder().id(2L).name("prison").id(1L).numberOfCells(1).villainList(List.of()).build();
        Mockito.when(prisonRepository.findById(2L)).thenReturn(Optional.of(prison));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () ->
                        villainService.create(
                                new CreateVillainRequest(1L,"name", "lastName", "country",
                                        2L, "01.01.2022", 100.55, true,
                                        101L, "robbery")));

        assertEquals("No offense with id: 101", exception.getMessage());
    }

    @Test
    public void createVillain() {
        Prison prison = Prison.builder().id(2L).name("prison").numberOfCells(1).villainList(List.of()).build();
        Offense offense = Offense.builder().id(101L).build();
        Villain villain = Villain.builder().firstName("name").lastName("lastName")
                .prison(prison)
                .offense(offense)
                .build();

        Mockito.when(prisonRepository.findById(2L)).thenReturn(Optional.ofNullable(prison));
        Mockito.when(offenseRepository.findById(101L)).thenReturn(Optional.ofNullable(offense));

        villainService.create(new CreateVillainRequest(1L,"name", "lastName", "country",
                2L, "01.01.2022", 100.55, true,
                101L, "robbery"));

        assertThat(villain)
                .hasFieldOrPropertyWithValue("firstName", "name")
                .hasFieldOrPropertyWithValue("lastName", "lastName");
        assertThat(prison)
                .hasFieldOrPropertyWithValue("id", 2L);
        assertThat(offense)
                .hasFieldOrPropertyWithValue("id", 101L);

    }

    @Test
    public void createVillainProperlyWithPrison() {
        Prison prison = Prison.builder().id(2L).numberOfCells(1).villainList(List.of()).build();
        Offense offense = Offense.builder().id(101L).build();

        Mockito.when(prisonRepository.findById(2L)).thenReturn(Optional.of(prison));
        Mockito.when(offenseRepository.findById(101L)).thenReturn(Optional.of(offense));

        villainService.create(new CreateVillainRequest(1L,"name", "lastName", "country",
                2L, "01.01.2022", 100.55, true,
                101L, "robbery"));

        assertThat(prison)
                .hasFieldOrPropertyWithValue("id", 2L);
    }

    @Test
    public void createVillainProperlyWithOffense() {
        Prison prison = Prison.builder().id(5L).numberOfCells(1).villainList(List.of()).build();
        Offense offense = Offense.builder().id(101L).build();

        Mockito.when(prisonRepository.findById(5L)).thenReturn(Optional.of(prison));
        Mockito.when(offenseRepository.findById(101L)).thenReturn(Optional.of(offense));

        villainService.create(new CreateVillainRequest(1L,"name", "lastName", "country",
                5L, "01.01.2022", 100.55, true,
                101L, "robbery"));

        assertThat(offense)
                .hasFieldOrPropertyWithValue("id", 101L);
    }

    @Test
    public void whenAddVillainToPrison_whilePrisonIsFull_fail() {
        Prison prison = Prison.builder().id(1L).numberOfCells(0).villainList(List.of()).build();
        Mockito.when(prisonRepository.findById(1L)).thenReturn(Optional.of(prison));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () ->
                        villainService.create(
                                new CreateVillainRequest(1L, "firstName", "lastName",
                                        "country", 1L, "01.01.2022", 100.55,
                                        true, 101L,"robbery")));
        assertThat(exception)
                .hasMessageContaining("Cannot add new villain to the selected prison. The limit has been reached");
    }

}