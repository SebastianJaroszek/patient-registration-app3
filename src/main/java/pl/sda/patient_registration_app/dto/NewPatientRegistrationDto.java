package pl.sda.patient_registration_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPatientRegistrationDto {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
}
