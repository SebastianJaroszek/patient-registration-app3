package pl.sda.patient_registration_app.bo;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.patient_registration_app.annotations.EmailExistsException;
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

    @Transactional
    public Patient saveNewPatientToDB(NewPatientRegistrationDto newPatientRegistrationDto) throws EmailExistsException {

        if (emailExist(newPatientRegistrationDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: "
                            + newPatientRegistrationDto.getEmail());
        }


        Patient patient = utilsService.mapNewPatientRegistrationDtoToPatient(newPatientRegistrationDto);

      //  user.setRoles(Arrays.asList("ROLE_USER"));
        return patientsRepository.save(patient);

    }


    private boolean emailExist(String email) {
        Patient patient = patientsRepository.findByEmail(email);
        if (patient != null) {
            return true;
        }
        return false;
    }

}
