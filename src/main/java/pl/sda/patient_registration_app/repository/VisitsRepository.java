package pl.sda.patient_registration_app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.sda.patient_registration_app.dto.DoctorDto;
import pl.sda.patient_registration_app.dto.PatientDto;
import pl.sda.patient_registration_app.dto.VisitDto;
import pl.sda.patient_registration_app.entity.Doctor;
import pl.sda.patient_registration_app.entity.Patient;
import pl.sda.patient_registration_app.entity.Visit;
import pl.sda.patient_registration_app.type.DocSpecType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface VisitsRepository extends JpaRepository<Visit,Long>{

    List<Visit> findByDate(LocalDate date);

    List<Visit> findByDoctor(DoctorDto doctorDto);


    List<Visit> findByTime(LocalTime time);

    List<Visit> findByPatient(Patient patient);

    /*@Query("SELECT v FROM Visit v WHERE doctor = :doctor AND date = :date")
    List<Visit> findByDoctorAndDate(@Param("doctor") Doctor doctor, @Param("date") LocalDate date);*/
}
