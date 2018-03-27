package pl.sda.patient_registration_app.dto;

import lombok.*;
import pl.sda.patient_registration_app.annotations.PasswordMatches;
import pl.sda.patient_registration_app.type.DocSpecType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@PasswordMatches
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewDoctorDto {
//TODO walidacja dodawanego lekarza
    @NotNull
    @Size(min = 1, max = 100)
    private String name;
    private String lastName;
    private DocSpecType specialization;
    private Long id;
    private String login;
    private String password;

}
