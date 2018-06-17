package pl.sda.patient_registration_app.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.patient_registration_app.repository.PatientsRepository;

@Service
public class PatientFinder {

    private PatientsRepository patientsRepository;
    private UtilsService utilsService;

    @Autowired
    public PatientFinder(PatientsRepository patientsRepository, UtilsService utilsService) {
        this.patientsRepository = patientsRepository;
        this.utilsService = utilsService;
    }


}
