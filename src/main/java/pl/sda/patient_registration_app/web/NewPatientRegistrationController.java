package pl.sda.patient_registration_app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.patient_registration_app.bo.NewPatientRegistrationService;
import pl.sda.patient_registration_app.dto.NewPatientRegistrationDto;

@Controller
public class NewPatientRegistrationController {

    private final NewPatientRegistrationService newPatientRegistrationService;

    public NewPatientRegistrationController(NewPatientRegistrationService newPatientRegistrationService) {
        this.newPatientRegistrationService = newPatientRegistrationService;
    }

    @GetMapping(value = "/nowyUzytkownik")
    public ModelAndView showNewUserPage() {

        ModelAndView mav = new ModelAndView("nowyUzytkownik");
        mav.addObject("newUser", new NewPatientRegistrationDto());


        return mav;
    }

    @PostMapping(value = "/nowyUzytkownik/zarejestruj")
    public ModelAndView postNewUserPage(@ModelAttribute("newUser") NewPatientRegistrationDto newPatientRegistrationDto) {

        ModelAndView mav = new ModelAndView("rejestracjaWynik");

        newPatientRegistrationService.saveNewPatientToDB(newPatientRegistrationDto);


        return mav;
    }
//
//    @GetMapping(value = "/nowyUzytkownik/zarejestruj")
//    public ModelAndView showResultOfRegistration() {
//
//        ModelAndView mav = new ModelAndView("rejestracjaWynik");
//        return mav;
//    }
}
