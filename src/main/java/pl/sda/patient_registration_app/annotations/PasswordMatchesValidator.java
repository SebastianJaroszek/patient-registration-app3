package pl.sda.patient_registration_app.annotations;

import pl.sda.patient_registration_app.dto.NewPatientRegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        NewPatientRegistrationDto user = (NewPatientRegistrationDto) o;
        return user.getPassword().equals(user.getMatchingPassword());    }



}