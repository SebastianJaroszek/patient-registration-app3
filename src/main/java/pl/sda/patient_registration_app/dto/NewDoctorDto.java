package pl.sda.patient_registration_app.dto;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import pl.sda.patient_registration_app.annotations.PasswordMatches;
import pl.sda.patient_registration_app.type.DocSpecType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewDoctorDto {

    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    @Size(min = 1)
    private String lastName;

    private DocSpecType specialization;

    private Long id;

    @NotNull
    @Size(min = 1)
    private String login;

    @NotNull
    @Size(min = 7)
    private String password;

    @NotNull
    @Email
    @Size(min = 1)
    private String email;

}
