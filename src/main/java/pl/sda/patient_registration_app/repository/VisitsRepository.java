package pl.sda.patient_registration_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.patient_registration_app.dto.DoctorDto;
import pl.sda.patient_registration_app.entity.Patient;
import pl.sda.patient_registration_app.entity.Visit;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface VisitsRepository extends JpaRepository<Visit, Long> {

    List<Visit> findByDate(LocalDate date);

    List<Visit> findByDoctor(DoctorDto doctorDto);


    List<Visit> findByTime(LocalTime time);

    List<Visit> findByPatient(Patient patient);

    /*@Query("SELECT v FROM Visit v WHERE doctor = :doctor AND date = :date")
    List<Visit> findByDoctorAndDate(@Param("doctor") Doctor doctor, @Param("date") LocalDate date);*/
}
