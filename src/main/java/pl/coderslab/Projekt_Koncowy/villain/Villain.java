package pl.coderslab.Projekt_Koncowy.villain;

import lombok.*;
import pl.coderslab.Projekt_Koncowy.offense.Offense;
import pl.coderslab.Projekt_Koncowy.prison.Prison;
import pl.coderslab.Projekt_Koncowy.transfer.Transfer;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "villains")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Villain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "origin_country")
    private String originCountry;
    @Column(name = "date_of_conviction")
    @Pattern(regexp = "^([0][1-9]|[1-2][0-9]|[3][0-1])\\.([0][1-9]|[1][0,1,2])\\.[1-9]{1}[0-9]{3}$"
            , message = "Date Of Conviction must be in format dd.MM.yyyy")
    private String dateOfConviction;
    @Column(nullable = false, scale = 2)
    private Double deposit;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(nullable = false)
    private boolean alive;

    @ManyToOne(optional = false)
    private Offense offense;

    @ManyToOne(optional = false)
    private Prison prison;

    @ManyToOne
    private Transfer transfer;

    @PrePersist
    public void prePersist() {
        this.createdOn = LocalDateTime.now();
    }
}
