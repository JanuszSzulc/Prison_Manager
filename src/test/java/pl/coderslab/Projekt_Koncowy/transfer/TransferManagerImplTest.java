package pl.coderslab.Projekt_Koncowy.transfer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.coderslab.Projekt_Koncowy.offense.Offense;
import pl.coderslab.Projekt_Koncowy.prison.Prison;
import pl.coderslab.Projekt_Koncowy.prison.PrisonRepository;
import pl.coderslab.Projekt_Koncowy.villain.Villain;
import pl.coderslab.Projekt_Koncowy.villain.VillainRepository;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TransferManagerImplTest {

    @InjectMocks
    TransferManagerImpl transferManagerImpl;
    @Mock
    PrisonRepository prisonRepository;
    @Mock
    VillainRepository villainRepository;
    @Mock
    TransferRepository transferRepository;

    @Test
    public void createTransfer() {
        Prison prison = Prison.builder().id(2L).name("prison").numberOfCells(1).villainList(List.of()).build();
        Transfer transfer = Transfer.builder().id(1L).villainId(1L).destinationPrison(String.valueOf(prison.getId())).reason("transfer")
                .executionStatus(false).transferTime(1).build();

        assertThat(transfer)
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("destinationPrison", "2")
                .hasFieldOrPropertyWithValue("reason", "transfer")
                .hasFieldOrPropertyWithValue("executionStatus", false)
                .hasFieldOrPropertyWithValue("transferTime", 1L);
    }

    @Test
    public void addTransfer() {
        Prison prison = Prison.builder().id(25L).name("prison").numberOfCells(1).villainList(List.of()).build();
        Prison prison1 = Prison.builder().id(30L).name("prison").numberOfCells(1).villainList(List.of()).build();
        Mockito.when(prisonRepository.findById(30L)).thenReturn(Optional.of(prison1));
        Offense offense = Offense.builder().id(101L).build();
        Villain villain = Villain.builder().id(1L).firstName("name").lastName("lastName")
                .prison(prison).offense(offense).build();
        Mockito.when(villainRepository.findById(1L)).thenReturn(Optional.ofNullable(villain));
        Transfer transfer = Transfer.builder().id(1L)
                .villainId(1L).destinationPrison(String.valueOf(prison1.getId())).reason("transfer")
                .executionStatus(true).transferTime(0).build();

        transferManagerImpl.addTransfer(
                new TransferVillainRequest(
                        1L, 1L, 30L, "transfer",
                        true, 0));

        assertThat(villain)
                .hasFieldOrPropertyWithValue("prison", prison1);
        assertThat(transfer)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("destinationPrison","30")
                .hasFieldOrPropertyWithValue("reason","transfer")
                .hasFieldOrPropertyWithValue("executionStatus",true)
                .hasFieldOrPropertyWithValue("transferTime", 0L);
    }

    @Test
    public void testDelayedTransferSave() throws InterruptedException {
        Prison prison = Prison.builder().id(25L).name("prison").numberOfCells(1).villainList(List.of()).build();
        Prison prison1 = Prison.builder().id(30L).name("prison").numberOfCells(1).villainList(List.of()).build();
        Mockito.when(prisonRepository.findById(30L)).thenReturn(Optional.of(prison1));
        Offense offense = Offense.builder().id(101L).build();
        Villain villain = Villain.builder().id(1L).firstName("name").lastName("lastName")
                .prison(prison).offense(offense).build();
        Mockito.when(villainRepository.findById(1L)).thenReturn(Optional.ofNullable(villain));
        Transfer transfer = Transfer.builder().id(1L)
                .villainId(1L).destinationPrison(String.valueOf(prison1.getId())).reason("transfer")
                .executionStatus(true).transferTime(0).build();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                transferManagerImpl.addTransfer(
                        new TransferVillainRequest(
                                1L, 1L, 30L, "transfer",
                                true, 0));
                transferRepository.save(transfer);
            }
        }, 1000);

        Thread.sleep(2000);

        assertThat(villain)
                .hasFieldOrPropertyWithValue("prison", prison1);
        assertThat(transfer)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("destinationPrison","30")
                .hasFieldOrPropertyWithValue("reason","transfer")
                .hasFieldOrPropertyWithValue("executionStatus",true)
                .hasFieldOrPropertyWithValue("transferTime", 0L);
    }

    @Test
    public void whenCreateTransfer_withNonExistingVillain() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () ->
                        transferManagerImpl.addTransfer(
                                new TransferVillainRequest(1L, 1L, 2L, "transfer",
                                        false, 1)));

        assertEquals("No villain with id: 1", exception.getMessage());
    }

    @Test
    public void whenCreateTransfer_withNonExistingPrison() {
        Prison prison = Prison.builder().id(25L).name("prison").numberOfCells(1).villainList(List.of()).build();
        Offense offense = Offense.builder().id(101L).build();
        Villain villain = Villain.builder().id(1L).firstName("name").lastName("lastName")
                .prison(prison).offense(offense).build();
        Mockito.when(villainRepository.findById(1L)).thenReturn(Optional.ofNullable(villain));

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () ->
                        transferManagerImpl.addTransfer(
                                new TransferVillainRequest(
                                        1L, 1L, 30L, "transfer",
                                        false, 1)));

        assertEquals("No prison with id: 30", exception.getMessage());
    }

    @Test
    public void whenCreateTransfer_whileVillainIsAlreadyInDestinationPrison() {
        Prison prison = Prison.builder().id(25L)
                .name("prison").numberOfCells(1).villainList(List.of()).build();
        Offense offense = Offense.builder().id(101L).build();
        Villain villain = Villain.builder().id(1L).firstName("name").lastName("lastName")
                .prison(prison).offense(offense).build();
        Mockito.when(villainRepository.findById(1L)).thenReturn(Optional.ofNullable(villain));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () ->
                        transferManagerImpl.addTransfer(
                                new TransferVillainRequest(
                                        1L, 1L, 25L, "transfer",
                                        false, 1)));

        assertEquals("Villain is already in the this prison", exception.getMessage());

    }
}