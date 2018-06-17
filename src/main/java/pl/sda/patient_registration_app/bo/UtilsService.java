package pl.sda.patient_registration_app.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.patient_registration_app.dto.*;
import pl.sda.patient_registration_app.entity.Doctor;
import pl.sda.patient_registration_app.entity.Patient;
import pl.sda.patient_registration_app.entity.User;
import pl.sda.patient_registration_app.entity.Visit;
import pl.sda.patient_registration_app.type.DocSpecType;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PatientDto mapPatientToPatientDto(Patient patient) {
        if (patient != null) {
            return PatientDto.builder()
                    .id(patient.getId())
                    .name(patient.getFirstName())
                    .lastName(patient.getLastName())
                    .build();
        } else {
            return null;
        }
    }

    public DoctorDto mapDoctorToDoctorDto(Doctor doctor) {
        return DoctorDto.builder()
                .id(doctor.getId())
                .name(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .specialization(doctor.getSpecialization())
                .login(doctor.getLogin())
                .email(doctor.getEmail())
                .build();
    }

    public VisitDto mapVisitToVisitDto(Visit visit) {
        return VisitDto.builder()
                .id(visit.getId())
                .doctor(mapDoctorToDoctorDto(visit.getDoctor()))
                .patient(mapPatientToPatientDto(visit.getPatient()))
                .dayOfVisit(visit.getDate())
                .hourOfVisit(visit.getTime())
                .build();
    }

    public List<LocalTime> getHours() {
        List<LocalTime> hours = new ArrayList<>();
        for (int i = 6; i <= 19; i++) {
            hours.add(LocalTime.of(i, 0));
        }
        return hours;
    }

    public Patient mapNewPatientRegistrationDtoToPatient(NewUserRegistrationDto newUserRegistrationDto) {

        Patient patient = new Patient();
        patient.setFirstName(newUserRegistrationDto.getFirstName());
        patient.setLastName(newUserRegistrationDto.getLastName());
        patient.setLogin(newUserRegistrationDto.getLogin());
        patient.setPassword(passwordEncoder.encode(newUserRegistrationDto.getPassword()));


        return patient;
    }

    public MyUserPrincipalDto mapUserToMyUserPrincipalDto(User user) {

        List<GrantedAuthority> grantedAuthorities =
                getGrantedAuthorities(user);

        return MyUserPrincipalDto.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .id(user.getId())
                .authorites(grantedAuthorities)
                .build();

    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return authorities;
    }

    public Doctor mapDoctorDtoToDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setId(doctorDto.getId());
        return doctor;
    }


    public List<String> convertSpecEnum() {
        List<DocSpecType> docSpecTypes = Arrays.asList(DocSpecType.values());
        List<String> docSpecNames = docSpecTypes.stream()
                .map(s -> s.getName())
                .collect(Collectors.toList());
        docSpecNames.sort(String::compareTo);
        return docSpecNames;
    }


    public List<LocalTime> getClinicHoursOfWork() {
        List<LocalTime> hours = new ArrayList<>();
        for (int i = 6; i <= 20; i++) {
            hours.add(LocalTime.of(i, 0));
        }
        return hours;
    }
}
