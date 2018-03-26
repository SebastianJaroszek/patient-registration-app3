package pl.sda.patient_registration_app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.patient_registration_app.bo.PatientFinder;
import pl.sda.patient_registration_app.bo.VisitsFinder;
import pl.sda.patient_registration_app.bo.VisitsService;
import pl.sda.patient_registration_app.dto.MyUserPrincipalDto;
import pl.sda.patient_registration_app.dto.PatientDto;

@Controller

public class PatientController {

    private final VisitsService visitsService;
    private final VisitsFinder visitsFinder;
    private final PatientFinder patientFinder;

    @Autowired
    public PatientController(VisitsService visitsService, VisitsFinder visitsFinder, PatientFinder patientFinder) {
        this.visitsService = visitsService;
        this.visitsFinder = visitsFinder;
        this.patientFinder = patientFinder;
    }

    @GetMapping(value = "/wizytyPacjenta")
    public ModelAndView showPatientVisits(){
        ModelAndView mav = new ModelAndView("wizytyPacjenta");
        MyUserPrincipalDto myUserPrincipalDto = (MyUserPrincipalDto) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        mav.addObject("wizyty", visitsFinder.findVisitsByPatientDto(myUserPrincipalDto));
        return mav;

    }


}
