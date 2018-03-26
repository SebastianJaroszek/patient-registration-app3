package pl.sda.patient_registration_app.bo;


import org.springframework.stereotype.Service;
import pl.sda.patient_registration_app.dto.NewPatientRegistrationDto;
import pl.sda.patient_registration_app.entity.Patient;
import pl.sda.patient_registration_app.repository.PatientsRepository;

@Service
public class NewPatientRegistrationService {

    private final UtilsService utilsService;
    private final PatientsRepository patientsRepository;
    public NewPatientRegistrationService(UtilsService utilsService, PatientsRepository patientsRepository) {
        this.utilsService = utilsService;
        this.patientsRepository = patientsRepository;
    }

    public void saveNewPatientToDB(NewPatientRegistrationDto newPatientRegistrationDto){

        Patient patient = utilsService.mapNewPatientRegistrationDtoToPatient(newPatientRegistrationDto);

        patientsRepository.save(patient);

    }

}
