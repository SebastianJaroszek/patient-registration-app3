package pl.sda.patient_registration_app.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.patient_registration_app.annotations.EmailExistsException;
import pl.sda.patient_registration_app.bo.NewUserRegistrationService;
import pl.sda.patient_registration_app.dto.NewUserRegistrationDto;
import pl.sda.patient_registration_app.entity.Patient;

import javax.validation.Valid;

@Controller
public class NewPatientRegistrationController {

    private final NewUserRegistrationService newUserRegistrationService;

    public NewPatientRegistrationController(NewUserRegistrationService newUserRegistrationService) {
        this.newUserRegistrationService = newUserRegistrationService;
    }

    @GetMapping(value = "/nowyUzytkownik")
    public ModelAndView showNewUserPage() {

        ModelAndView mav = new ModelAndView("nowyUzytkownik");
        mav.addObject("newUser", new NewUserRegistrationDto());


        return mav;
    }

    @PostMapping(value = "/nowyUzytkownik/zarejestruj")
    public ModelAndView registerUserAccount(
            @ModelAttribute("newUser") @Valid NewUserRegistrationDto newUserRegistrationDto,
            BindingResult result,
            Errors errors) {

        Patient registered = null;

        if (!result.hasErrors()) {
            registered = createUserAccount(newUserRegistrationDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("bladRejestracji");
        } else {
            return new ModelAndView("rejestracjaWynik", "newUser", newUserRegistrationDto);
        }
    }

    private Patient createUserAccount(NewUserRegistrationDto newUserRegistrationDto, BindingResult result) {
        Patient registered = null;

        try {
            registered = newUserRegistrationService.saveNewPatientToDB(newUserRegistrationDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;

//    @PostMapping(value = "/nowyUzytkownik/zarejestruj")
////    public ModelAndView postNewUserPage(@ModelAttribute("newUser") @Valid NewUserRegistrationDto newUserRegistrationDto) {
////
////        ModelAndView mav = new ModelAndView("rejestracjaWynik");
////
////        newPatientRegistrationService.saveNewPatientToDB(newUserRegistrationDto);
////
////
////        return mav;
////    }


}}
