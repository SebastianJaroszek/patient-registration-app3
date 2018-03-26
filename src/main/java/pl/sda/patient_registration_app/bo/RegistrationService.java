package pl.sda.patient_registration_app.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import pl.sda.patient_registration_app.entity.Doctor;
import pl.sda.patient_registration_app.entity.Patient;
import pl.sda.patient_registration_app.entity.Visit;
import pl.sda.patient_registration_app.repository.DoctorsRepository;
import pl.sda.patient_registration_app.repository.PatientsRepository;
import pl.sda.patient_registration_app.repository.VisitsRepository;
import pl.sda.patient_registration_app.type.DocSpecType;

import java.time.LocalDate;
import java.time.LocalTime;

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
