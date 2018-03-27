package pl.sda.patient_registration_app.entity;

import lombok.*;
import pl.sda.patient_registration_app.type.DocSpecType;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor extends User{

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "doctor")
    private Set<Visit> visits;

    @Column(name = "specialization")
    @Enumerated(value = EnumType.STRING)
    private DocSpecType specialization;

    @OneToMany(mappedBy = "doctor")
    private Set<DoctorTimetable> timetables;

}
