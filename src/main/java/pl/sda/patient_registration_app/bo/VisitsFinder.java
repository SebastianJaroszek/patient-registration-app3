package pl.sda.patient_registration_app.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.patient_registration_app.dto.DoctorDto;
import pl.sda.patient_registration_app.dto.MyUserPrincipalDto;
import pl.sda.patient_registration_app.dto.VisitDto;
import pl.sda.patient_registration_app.entity.Visit;
import pl.sda.patient_registration_app.repository.PatientsRepository;
import pl.sda.patient_registration_app.repository.VisitsRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitsFinder {

    private VisitsRepository visitsRepository;
    private UtilsService utilsService;
    private VisitsService visitsService;
    private PatientsRepository patientsRepository;

    @Autowired
    public VisitsFinder(VisitsRepository visitsRepository, UtilsService utilsService, VisitsService visitsService, PatientsRepository patientsRepository) {
        this.visitsRepository = visitsRepository;
        this.utilsService = utilsService;
        this.visitsService = visitsService;
        this.patientsRepository = patientsRepository;
    }

    @Transactional
    public List<VisitDto> showAllVisits() {


        return visitsRepository.findAll().stream()
                .map(v -> utilsService.mapVisitToVisitDto(v))
                .collect(Collectors.toList());
    }

    public List<VisitDto> getVisitsByDate(LocalDate date) {
        List<Visit> foundedByDate = visitsRepository.findByDate(date);
        return foundedByDate.stream()
                .map(v -> utilsService.mapVisitToVisitDto(v))
                .collect(Collectors.toList());
    }

    public List<VisitDto> filterVisitsDtoByHour(List<VisitDto> visitsDto, LocalTime time) {
        return visitsDto.stream()
                .filter(v -> v.getHourOfVisit().equals(time))
                .collect(Collectors.toList());
    }

    public List<VisitDto> getVisitsByTime(LocalTime time) {
        List<Visit> foundedByHour = visitsRepository.findByTime(time);
        return foundedByHour.stream()
                .map(v -> utilsService.mapVisitToVisitDto(v))
                .collect(Collectors.toList());
    }

    private List<VisitDto> getVisitsByDoctor(DoctorDto doctorDto) {
        List<Visit> foundedDoctors = visitsRepository.findByDoctor(doctorDto);
        return foundedDoctors.stream()
                .map(v -> utilsService.mapVisitToVisitDto(v))
                .collect(Collectors.toList());

    }

    private List<VisitDto> filterVisitsDtoByDoctor(List<VisitDto> visitsDto, DoctorDto doctorDto) {
        return visitsDto.stream()
                .filter(v -> v.getDoctor().equals(doctorDto))
                .collect(Collectors.toList());
    }

//    private List<Visit> getVisitsByPatient(PatientDto patientDto){
//       return patientsRepository.findByPatient(patientDto.getId());
//
//    }

    public List<VisitDto> findVisitsByPatientDto(MyUserPrincipalDto myUserPrincipalDto) {
        List<Visit> foundedVisits = visitsRepository.findByPatient(patientsRepository.findOne(myUserPrincipalDto.getId()));
        return foundedVisits.stream()
                .map(v -> utilsService.mapVisitToVisitDto(v))
                .collect(Collectors.toList());

    }

//    public List<Visit> findVisitsByPatientDto(PatientDto patientDto) {
//        return visitDto.stream()
//                .filter(v->v.getId().equals(patientDto))
//                .collect(Collectors.toList());
//
//    }
}
