package pl.sda.patient_registration_app.dto;

import lombok.*;
import pl.sda.patient_registration_app.entity.Doctor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewTimetableDto {

    private List<Long> doctorsId;
    private List<Boolean> isChecked;

    @Enumerated(value = EnumType.STRING)
    private DayOfWeek dayOfWeek;
    private LocalTime fromTime;
    private LocalTime toTime;


}
