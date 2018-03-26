package pl.sda.patient_registration_app.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.patient_registration_app.dto.DoctorDto;
import pl.sda.patient_registration_app.dto.VisitDto;
import pl.sda.patient_registration_app.entity.Doctor;
import pl.sda.patient_registration_app.repository.DoctorsRepository;
import pl.sda.patient_registration_app.repository.PatientsRepository;
import pl.sda.patient_registration_app.repository.VisitsRepository;
import pl.sda.patient_registration_app.type.DocSpecType;

import java.util.List;
import java.util.stream.Collectors;

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


    public void addDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorDto.getName());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setSpecialization(doctorDto.getSpecialization());
        doctor.setLogin(doctorDto.getLogin());
        doctor.setPassword(doctorDto.getPassword());
        doctorsRepository.save(doctor);
    }
}
