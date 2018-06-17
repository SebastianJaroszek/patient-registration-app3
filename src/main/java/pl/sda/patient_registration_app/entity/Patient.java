package pl.sda.patient_registration_app.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient extends User {

    @OneToMany(mappedBy = "patient")

    private Set<Visit> visits;

    @Column(name = "email")
    private String email;

}
