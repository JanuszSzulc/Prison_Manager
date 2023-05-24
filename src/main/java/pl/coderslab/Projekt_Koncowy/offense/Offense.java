package pl.coderslab.Projekt_Koncowy.offense;

import lombok.*;
import pl.coderslab.Projekt_Koncowy.prison.Prison;
import pl.coderslab.Projekt_Koncowy.villain.Villain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offenses")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OffenseLevel level;
    private String description;

    @OneToMany(mappedBy = "offense")
    @ToString.Exclude
    private List<Villain> villainList = new ArrayList<>();
}
