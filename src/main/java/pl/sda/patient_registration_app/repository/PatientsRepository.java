package pl.sda.patient_registration_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.patient_registration_app.dto.PatientDto;
import pl.sda.patient_registration_app.entity.Patient;
import pl.sda.patient_registration_app.entity.Visit;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PatientsRepository extends JpaRepository<Patient,Long>{

}
