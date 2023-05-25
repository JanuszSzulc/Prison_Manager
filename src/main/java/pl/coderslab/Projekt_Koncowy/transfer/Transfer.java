package pl.coderslab.Projekt_Koncowy.transfer;

import lombok.*;
import pl.coderslab.Projekt_Koncowy.prison.Prison;
import pl.coderslab.Projekt_Koncowy.villain.Villain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transfers")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "villain_id")
    private Long villainId;
    @Column(name = "destinaton_prison", nullable = false)
    private String destinationPrison;
    private String reason;
    @Column(name = "execution_status")
    private boolean executionStatus;
    @Column(name = "transfer_time")
    private long transferTime;

    @OneToMany(mappedBy = "transfer")
    @ToString.Exclude
    private List<Villain> villain = new ArrayList<>();

    @OneToMany(mappedBy = "transfer")
    @ToString.Exclude
    private List<Prison> prisons = new ArrayList<>();
}
