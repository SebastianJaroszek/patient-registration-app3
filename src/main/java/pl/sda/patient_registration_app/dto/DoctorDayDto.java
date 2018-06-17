package pl.sda.patient_registration_app.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sda.patient_registration_app.bo.UtilsService;

import java.time.LocalDate;
import java.util.List;

@Component
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDayDto {


    private UtilsService utilsService;

    private DoctorDto doctorDto;
    private List<VisitDto> visits;
    private LocalDate date;

    @Autowired
    public DoctorDayDto(UtilsService utilsService) {
        this.utilsService = utilsService;
    }


}
