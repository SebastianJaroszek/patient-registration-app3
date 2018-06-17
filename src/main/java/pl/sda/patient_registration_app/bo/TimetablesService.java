package pl.sda.patient_registration_app.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.patient_registration_app.dto.NewTimetableDto;
import pl.sda.patient_registration_app.entity.DoctorTimetable;
import pl.sda.patient_registration_app.repository.DoctorTimetablesRepository;
import pl.sda.patient_registration_app.repository.DoctorsRepository;

@Service
public class TimetablesService {

    private DoctorTimetablesRepository timetablesRepository;
    private DoctorsRepository doctorsRepository;

    @Autowired
    public TimetablesService(DoctorTimetablesRepository timetablesRepository, DoctorsRepository doctorsRepository) {
        this.timetablesRepository = timetablesRepository;
        this.doctorsRepository = doctorsRepository;
    }

    public void addNewTimetableToDB(NewTimetableDto newTimetableDto) {
        for (Long doctorId : newTimetableDto.getDoctorsId()) {
            DoctorTimetable timetable = DoctorTimetable.builder()
                    .doctor(doctorsRepository.getOne(doctorId))
                    .dayOfWeek(newTimetableDto.getDayOfWeek())
                    .fromTime(newTimetableDto.getFromTime())
                    .toTime(newTimetableDto.getToTime())
                    .build();
            timetablesRepository.save(timetable);
        }
    }
}
