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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UtilsService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    public PatientDto mapPatientToPatientDto(Patient patient) {
        //List<VisitDto> visits = mapVisitsToVisitsDto(patient.getVisits());
        if (patient != null) {
            return PatientDto.builder()
                    .id(patient.getId())
                    .name(patient.getFirstName())
                    .lastName(patient.getLastName())
                    //.plannedVisits(visits)
                    .build();
        } else {
            return null;
        }
    }

    public DoctorDto mapDoctorToDoctorDto(Doctor doctor) {
        //List<VisitDto> visit = mapVisitsToVisitsDto(doctor.getVisits());
        return DoctorDto.builder()
                .id(doctor.getId())
                .name(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .specialization(doctor.getSpecialization())
                .login(doctor.getLogin())
                .email(doctor.getEmail())
                //.visits(visit)
                .build();
    }

    private List<VisitDto> mapVisitsToVisitsDto(Set<Visit> visits) {
        return visits.stream()
                .map(v -> mapVisitToVisitDto(v))
                .collect(Collectors.toList());
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


    public Patient mapPatientDtoToPatient(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setId(patientDto.getId());
        return patient;
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

    public MyUserPrincipalDto mapPatientToMyUserPrincipal(Patient patient) {


        return MyUserPrincipalDto.builder()
                .login(patient.getLogin())
                .password(patient.getPassword())
                .id(patient.getId()).build();
    }

    public MyUserPrincipalDto mapDoctorToMyUserPrincipal(Doctor doctor) {


        return MyUserPrincipalDto.builder()
                .login(doctor.getLogin())
                .password(doctor.getPassword())
                .id(doctor.getId()).build();
    }

    public Doctor mapDoctorDtoToDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setId(doctorDto.getId());
        return doctor;
    }


    public List<String> convertSpecEnum() { //testy do tego!
        //List<String> afterConvert = new ArrayList<>();
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
