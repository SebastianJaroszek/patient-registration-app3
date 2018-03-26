package pl.sda.patient_registration_app.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.patient_registration_app.dto.DoctorDayDto;
import pl.sda.patient_registration_app.dto.DoctorDto;
import pl.sda.patient_registration_app.dto.RegisterDto;
import pl.sda.patient_registration_app.dto.VisitDto;
import pl.sda.patient_registration_app.entity.Visit;
import pl.sda.patient_registration_app.repository.VisitsRepository;
import pl.sda.patient_registration_app.type.DocSpecType;
import pl.sda.patient_registration_app.type.VisitStatusType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorDaysService {

    private VisitsRepository visitsRepository;
    private UtilsService utilsService;
    private DoctorsFinder doctorsFinder;
    private VisitsService visitsService;


    @Autowired
    public DoctorDaysService(VisitsRepository visitsRepository, UtilsService utilsService, DoctorsFinder doctorsFinder,
                             VisitsService visitsService) {
        this.visitsRepository = visitsRepository;
        this.utilsService = utilsService;
        this.doctorsFinder = doctorsFinder;
        this.visitsService = visitsService;
    }

    private void addStatusToVisit(VisitDto visitDto) {
        if (visitDto.getPatient() != null) {
            visitDto.setStatus(VisitStatusType.UNAVAILABLE);
        } else {
            visitDto.setStatus(VisitStatusType.AVAILABLE);
        }
    }

    public void addVisitsToDayVisitList(DoctorDayDto doctorDayDto) {
        List<Visit> visitsByDate = visitsRepository.findByDate(doctorDayDto.getDate());
        List<VisitDto> visitsByDoctor = visitsByDate.stream()
                .filter(v -> v.getDoctor().getId().equals(doctorDayDto.getDoctorDto().getId()))
                .map(v -> utilsService.mapVisitToVisitDto(v))
                .collect(Collectors.toList());

        for (VisitDto visit : visitsByDoctor) {
            addStatusToVisit(visit);
        }

        doctorDayDto.getVisits().addAll(visitsByDoctor);

        fillListWithNoExistingVisits(doctorDayDto.getVisits());
    }

    private void fillListWithNoExistingVisits(List<VisitDto> visitsDto) {
        LocalTime temptime = LocalTime.of(0, 0);
        for (int i = 6; i <= 19; i++) {
            temptime = LocalTime.of(i, 0);
            boolean isContaining = false;
            for (VisitDto visitDto : visitsDto) {
                if (visitDto.getHourOfVisit().equals(temptime)) {
                    isContaining = true;
                    break;
                }
            }
            if (!isContaining) {
                visitsDto.add(VisitDto.builder()
                        .status(VisitStatusType.NOT_EXIST)
                        .hourOfVisit(LocalTime.of(i, 0))
                        .build());
            }

            /*for (VisitDto visitDto : visitsDto) {
                if (visitDto.getHourOfVisit().equals(temptime)) {
                    continue;
                }
                tempVisits.add(VisitDto.builder()
                        .status(VisitStatusType.NOT_EXIST)
                        .build());
            }
            visitsDto.add(VisitDto.builder().status(VisitStatusType.NOT_EXIST)
                    .build());*/
        }
    }

    private List<VisitDto> sortByHour(List<VisitDto> visitsDto) {
        return visitsDto.stream()
                .sorted(VisitDto::compareTo)
                .collect(Collectors.toList());
    }

    public List<DoctorDayDto> createDayDtoFromDoctorDtoAndDate(LocalDate date, DocSpecType docSpecType) {

        List<DoctorDayDto> doctorDaysDto = new ArrayList<>();
        List<DoctorDto> doctorsDto = doctorsFinder.findBySpecialization(docSpecType);

        visitsService.createAvailableVisitsByDoctorTimetable(date, doctorsDto);

        for (DoctorDto doctorDto : doctorsDto) {
            DoctorDayDto doctorDayDto = DoctorDayDto.builder()
                    .doctorDto(doctorDto)
                    .date(date)
                    .visits(doctorDto.getVisits())
                    .build();
            addVisitsToDayVisitList(doctorDayDto);
            //sortByHour(doctorDayDto.getVisits());
            doctorDayDto.setVisits(sortByHour(doctorDayDto.getVisits()));
            doctorDaysDto.add(doctorDayDto);
        }

        return doctorDaysDto;

    }

}
