package pl.sda.patient_registration_app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.patient_registration_app.bo.VisitsFinder;
import pl.sda.patient_registration_app.dto.MyUserPrincipalDto;

@Controller
public class PokazywarkaController {


    private final VisitsFinder visitsFinder;

    @Autowired
    public PokazywarkaController(VisitsFinder visitsFinder) {
        this.visitsFinder = visitsFinder;
    }

    @GetMapping("/pokazywarkaWizyt")
    public ModelAndView showPokazywarkaPage() {

        ModelAndView mav = new ModelAndView("pokazywarkaWizyt");

        mav.addObject("wizyty", visitsFinder.showAllVisits());

        MyUserPrincipalDto myUserPrincipalDto = (MyUserPrincipalDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mav.addObject("userID", myUserPrincipalDto.getId());
        return mav;
    }

}