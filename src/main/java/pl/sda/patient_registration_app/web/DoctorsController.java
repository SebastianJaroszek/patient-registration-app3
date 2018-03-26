package pl.sda.patient_registration_app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.patient_registration_app.bo.DoctorsFinder;
import pl.sda.patient_registration_app.bo.DoctorsService;
import pl.sda.patient_registration_app.bo.RegistrationFinder;
import pl.sda.patient_registration_app.bo.RegistrationService;

@Controller
public class DoctorsController {
    private final RegistrationFinder registrationFinder;
    private final RegistrationService registrationService;
    private final DoctorsService doctorsService;
    private final DoctorsFinder doctorsFinder;

    @Autowired
    public DoctorsController(RegistrationFinder registrationFinder, RegistrationService registrationService, DoctorsService doctorsService, DoctorsFinder doctorsFinder) {
        this.registrationFinder = registrationFinder;
        this.registrationService = registrationService;
        this.doctorsService = doctorsService;
        this.doctorsFinder = doctorsFinder;
    }

    @GetMapping("/doktorzy")
    public ModelAndView showDoctorsPage() {

        ModelAndView mav = new ModelAndView("doktorzy");

        mav.addObject("doctors", doctorsFinder.showAllDoctors());

        return mav;
    }
}
