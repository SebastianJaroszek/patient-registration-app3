package pl.sda.patient_registration_app.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.patient_registration_app.dto.DoctorDto;
import pl.sda.patient_registration_app.dto.NewDoctorDto;
import pl.sda.patient_registration_app.entity.Doctor;
import pl.sda.patient_registration_app.repository.DoctorsRepository;
import pl.sda.patient_registration_app.repository.PatientsRepository;
import pl.sda.patient_registration_app.repository.VisitsRepository;
import pl.sda.patient_registration_app.type.DocSpecType;

@Service
public class DoctorsService {


    private VisitsRepository visitsRepository;
    private PatientsRepository patientsRepository;
    private DoctorsRepository doctorsRepository;

    @Autowired
    public DoctorsService(VisitsRepository visitsRepository, PatientsRepository patientsRepository, DoctorsRepository doctorsRepository) {
        this.visitsRepository = visitsRepository;
        this.patientsRepository = patientsRepository;
        this.doctorsRepository = doctorsRepository;
    }

    private void addDoctor(String firstName, String lastName, DocSpecType docSpecType) {

        Doctor doctor = new Doctor();

        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setSpecialization(docSpecType);
        doctorsRepository.save(doctor);

    }

    public void fillDBwithDoctors() {

        /*addDoctor("Mariusz", "Putas", DocSpecType.UROLOGIST);
        addDoctor("Janusz", "Fijut", DocSpecType.UROLOGIST);
        addDoctor("Sylweriusz", "Psikuta", DocSpecType.UROLOGIST);
        addDoctor("Adam", "Kula", DocSpecType.HEMATOLOGIST);*/

    }


    @Transactional
    public void addDoctor(NewDoctorDto newDoctorDto) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(newDoctorDto.getName());
        doctor.setLastName(newDoctorDto.getLastName());
        doctor.setSpecialization(newDoctorDto.getSpecialization());
        doctor.setLogin(newDoctorDto.getLogin());
        doctor.setPassword(newDoctorDto.getPassword());
        doctorsRepository.save(doctor);
    }
}
