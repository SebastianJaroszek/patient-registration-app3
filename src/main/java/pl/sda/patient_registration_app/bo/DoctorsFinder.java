package pl.sda.patient_registration_app.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.patient_registration_app.dto.DoctorDto;
import pl.sda.patient_registration_app.repository.DoctorsRepository;
import pl.sda.patient_registration_app.type.DocSpecType;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorsFinder {

    private DoctorsRepository doctorsRepository;
    private UtilsService utilsService;

    @Autowired
    public DoctorsFinder(DoctorsRepository doctorsRepository, UtilsService utilsService) {
        this.doctorsRepository = doctorsRepository;
        this.utilsService = utilsService;
    }

    @Transactional
    public List<DoctorDto> showAllDoctors() {
        return doctorsRepository.findAll().stream()
                .map(v -> utilsService.mapDoctorToDoctorDto(v))
                .collect(Collectors.toList());
    }

    public List<DoctorDto> findBySpecialization(DocSpecType docSpecType) {
        return doctorsRepository.findBySpecialization(docSpecType).stream()
                .map(d -> utilsService.mapDoctorToDoctorDto(d))
                .collect(Collectors.toList());
    }

    public DoctorDto findByLogin(String login) {
        return utilsService.mapDoctorToDoctorDto(doctorsRepository.findByLogin(login));
    }
}
