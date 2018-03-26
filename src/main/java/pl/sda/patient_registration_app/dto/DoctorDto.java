package pl.sda.patient_registration_app.dto;

import lombok.*;
import pl.sda.patient_registration_app.type.DocSpecType;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {


    private String name;
    private String lastName;
    private DocSpecType specialization;
    private Long id;
    private String login;
    private String password;
    private List<VisitDto> visits;
    private List<DoctorTimetableDto> timetables;


}
