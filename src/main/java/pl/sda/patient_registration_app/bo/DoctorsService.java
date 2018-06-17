package pl.sda.patient_registration_app.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.patient_registration_app.dto.NewDoctorDto;
import pl.sda.patient_registration_app.dto.NewTimetableDto;
import pl.sda.patient_registration_app.entity.Doctor;
import pl.sda.patient_registration_app.repository.DoctorsRepository;
import pl.sda.patient_registration_app.repository.PatientsRepository;
import pl.sda.patient_registration_app.repository.VisitsRepository;
import pl.sda.patient_registration_app.type.DocSpecType;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorsService {

    private DoctorsRepository doctorsRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DoctorsService(DoctorsRepository doctorsRepository, PasswordEncoder passwordEncoder) {
        this.doctorsRepository = doctorsRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public Doctor addDoctor(NewDoctorDto newDoctorDto) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(newDoctorDto.getName());
        doctor.setLastName(newDoctorDto.getLastName());
        doctor.setSpecialization(newDoctorDto.getSpecialization());
        doctor.setLogin(newDoctorDto.getLogin());
        doctor.setPassword(passwordEncoder.encode(newDoctorDto.getPassword()));
        doctor.setEmail(newDoctorDto.getEmail());
        doctorsRepository.save(doctor);
        return doctor;
    }

    public List<Long> getCheckedDoctorsId(NewTimetableDto newTimetableDto) {
        List<Long> ids = new ArrayList<>();
        int size = newTimetableDto.getIsChecked().size();
        for (int i = 0; i < size; i++) {
            if (newTimetableDto.getIsChecked().get(i) != null && newTimetableDto.getIsChecked().get(i)) {
                ids.add(newTimetableDto.getDoctorsId().get(i));
            }
        }
        return ids;
    }
}
