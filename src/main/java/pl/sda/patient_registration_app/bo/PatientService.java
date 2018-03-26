package pl.sda.patient_registration_app.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.patient_registration_app.dto.PatientDto;
import pl.sda.patient_registration_app.dto.VisitDto;
import pl.sda.patient_registration_app.repository.DoctorsRepository;
import pl.sda.patient_registration_app.repository.PatientsRepository;
import pl.sda.patient_registration_app.repository.VisitsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private PatientsRepository patientsRepository;
    private DoctorsRepository doctorsRepository;
    private VisitsRepository visitsRepository;

    @Autowired
    public PatientService(PatientsRepository patientsRepository, DoctorsRepository doctorsRepository, VisitsRepository visitsRepository) {
        this.patientsRepository = patientsRepository;
        this.doctorsRepository = doctorsRepository;
        this.visitsRepository = visitsRepository;
    }


}
