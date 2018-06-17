package pl.sda.patient_registration_app.bo;

import org.postgresql.shaded.com.ongres.scram.common.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.patient_registration_app.dto.DoctorDto;
import pl.sda.patient_registration_app.dto.RegisterDto;
import pl.sda.patient_registration_app.dto.VisitDto;
import pl.sda.patient_registration_app.entity.Doctor;
import pl.sda.patient_registration_app.entity.DoctorTimetable;
import pl.sda.patient_registration_app.entity.Visit;
import pl.sda.patient_registration_app.repository.DoctorTimetablesRepository;
import pl.sda.patient_registration_app.repository.DoctorsRepository;
import pl.sda.patient_registration_app.repository.PatientsRepository;
import pl.sda.patient_registration_app.repository.VisitsRepository;
import pl.sda.patient_registration_app.type.VisitStatusType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitsService {


    private VisitsRepository visitsRepository;
    private PatientsRepository patientsRepository;
    private DoctorsRepository doctorsRepository;
    private DoctorTimetablesRepository timetablesRepository;
    private UtilsService utilsService;

    @Autowired
    public VisitsService(VisitsRepository visitsRepository, PatientsRepository patientsRepository,
                         DoctorsRepository doctorsRepository, DoctorTimetablesRepository timetablesRepository,
                         UtilsService utilsService) {
        this.visitsRepository = visitsRepository;
        this.patientsRepository = patientsRepository;
        this.doctorsRepository = doctorsRepository;
        this.timetablesRepository = timetablesRepository;
        this.utilsService = utilsService;
    }


    public void addVisit(LocalDate day, LocalTime time, Doctor doctor) {

        Visit visit = Visit.builder()
                .date(day)
                .time(time)
                .doctor(doctor)
                .build();
        visitsRepository.save(visit);

    }

    @Transactional
    public void registerPatient(RegisterDto registerDto, Long patientId) {
        Preconditions.checkNotNull(patientId, "ID pacjenta jest nullem");
        Visit newVisit = Visit.builder()
                .date(registerDto.getDate())
                .time(registerDto.getTime())
                .doctor(doctorsRepository.findOne(registerDto.getDoctorId()))
                .patient(patientsRepository.findOne(patientId))
                .build();

        visitsRepository.save(newVisit);
    }

    private DoctorTimetable getTimetableForDayOfWeek(LocalDate date, DoctorDto doctorDto) {
        return timetablesRepository
                .findByDayOfWeekAndDoctor(date.getDayOfWeek(), utilsService.mapDoctorDtoToDoctor(doctorDto));
    }

    public void createAvailableVisitsByDoctorTimetable(LocalDate date, List<DoctorDto> doctorsDto) {
        for (DoctorDto doctorDto : doctorsDto) {
            doctorDto.setVisits(createAvailableVisitsByDoctorTimetable(date, doctorDto));
        }
    }

    private boolean isVisitAvailable(List<Visit> unavaidableVisits, LocalTime time, LocalDate date, DoctorDto doctorDto) {
        List<VisitDto> unavaidableVisitsDto = unavaidableVisits.stream()
                .map(v -> utilsService.mapVisitToVisitDto(v))
                .collect(Collectors.toList());
        VisitDto foundedVisit = unavaidableVisitsDto.stream()
                .filter(v -> v.getHourOfVisit().equals(time))
                .filter(v -> v.getDayOfVisit().equals(date))
                .filter(v -> v.getDoctor().getId().equals(doctorDto.getId()))
                .findFirst().orElse(null);
        return foundedVisit == null;
    }

    private List<VisitDto> createAvailableVisitsByDoctorTimetable(LocalDate date, DoctorDto doctorDto) {
        List<VisitDto> availableVisits = new ArrayList<>();

        DoctorTimetable timetable = getTimetableForDayOfWeek(date, doctorDto);
        if (timetable != null) {
            int from = timetable.getFromTime().getHour();
            int to = timetable.getToTime().getHour();
            List<Visit> unavailableVisits = visitsRepository.findAll();
            for (int i = from; i <= to; i++) {
                if (isVisitAvailable(unavailableVisits, LocalTime.of(i, 0), date, doctorDto)) {
                    availableVisits.add(VisitDto.builder()
                            .hourOfVisit(LocalTime.of(i, 0))
                            .dayOfVisit(date)
                            .status(VisitStatusType.AVAILABLE)
                            .doctor(doctorDto)
                            .build()
                    );
                }
            }
        }

        return availableVisits;
    }


}
