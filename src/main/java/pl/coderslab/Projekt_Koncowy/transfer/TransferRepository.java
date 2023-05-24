package pl.coderslab.Projekt_Koncowy.transfer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.Projekt_Koncowy.transfer.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {



}
