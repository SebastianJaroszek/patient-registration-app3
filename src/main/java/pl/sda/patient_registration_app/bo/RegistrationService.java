package pl.sda.patient_registration_app.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.patient_registration_app.entity.Patient;
import pl.sda.patient_registration_app.repository.PatientsRepository;

@Service
public class RegistrationService {


    PatientsRepository patientsRepository;

    @Autowired
    public RegistrationService(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    public void addPatietnToDB() {


        Patient patient = Patient.builder().build();
        patient.setFirstName("Andrzej");
        patient.setLastName("Duda");
        patient.setLogin("dupa");
        patient.setPassword("dupa");


        patientsRepository.save(patient);
    }
}
