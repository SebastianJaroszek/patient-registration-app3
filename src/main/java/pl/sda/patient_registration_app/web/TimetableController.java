package pl.sda.patient_registration_app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TimetableController {

    @GetMapping("/dodawanieHarmonogramu")
    public ModelAndView showAddTimetablePage(){
        ModelAndView mav = new ModelAndView("dodawanieHarmonogramu");
        return mav;
    }

}
