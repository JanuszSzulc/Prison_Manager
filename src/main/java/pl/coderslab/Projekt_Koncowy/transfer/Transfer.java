package pl.coderslab.Projekt_Koncowy.transfer;

import lombok.*;
import pl.coderslab.Projekt_Koncowy.prison.Prison;
import pl.coderslab.Projekt_Koncowy.villain.Villain;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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
    @Column(name = "destinaton_prison", nullable = false)
    private String destinationPrison;
    private String reason;
    @Column(name = "execution_status")
    private boolean executionStatus;
    @Column(name = "transfer_date")
    @Pattern(regexp = "^([0][1-9]|[1-2][0-9]|[3][0-1])\\.([0][1-9]|[1][0,1,2])\\.[1-9]{1}[0-9]{3}$"
            , message = "Transfer date must be in format dd.MM.yyyy")
    private String transferDate;

    @OneToMany(mappedBy = "transfer")
    @ToString.Exclude
    private List<Villain> villain = new ArrayList<>();

    @OneToMany(mappedBy = "transfer")
    @ToString.Exclude
    private List<Prison> prisons = new ArrayList<>();
}
