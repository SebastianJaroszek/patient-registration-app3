package pl.sda.patient_registration_app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.patient_registration_app.bo.*;
import pl.sda.patient_registration_app.dto.NewDoctorDto;
import pl.sda.patient_registration_app.entity.Doctor;
import pl.sda.patient_registration_app.type.DocSpecType;

import javax.validation.Valid;

@Controller
public class DoctorsController {
    private final RegistrationFinder registrationFinder;
    private final RegistrationService registrationService;
    private final DoctorsService doctorsService;
    private final DoctorsFinder doctorsFinder;
    private final UtilsService utilsService;

    @Autowired
    public DoctorsController(RegistrationFinder registrationFinder, RegistrationService registrationService,
                             DoctorsService doctorsService, DoctorsFinder doctorsFinder, UtilsService utilsService) {
        this.registrationFinder = registrationFinder;
        this.registrationService = registrationService;
        this.doctorsService = doctorsService;
        this.doctorsFinder = doctorsFinder;
        this.utilsService = utilsService;
    }

    @GetMapping("/doktorzy")
    public ModelAndView showDoctorsPage() {

        ModelAndView mav = new ModelAndView("doktorzy");

        mav.addObject("doctors", doctorsFinder.showAllDoctors());

        return mav;
    }

    @GetMapping("/dodajLekarza")
    public String addDoctorPage(Model model) {
        model.addAttribute("newDoctor", new NewDoctorDto());
        model.addAttribute("docSpecEnum", utilsService.convertSpecEnum());
        return "dodawanieLekarza";
    }

    @PostMapping("/dodajLekarza")
    public ModelAndView addDoctorToDB(@ModelAttribute("newDoctor") @Valid NewDoctorDto newDoctorDto,
                                      BindingResult result,
                                      Errors errors,
                                      @RequestParam("specType") String specType) {

        Doctor registered = null;

        ModelAndView mav = new ModelAndView("dodawanieLekarzaWynik");
        if (!result.hasErrors()) {
            DocSpecType docSpecType = DocSpecType.findByName(specType);
            newDoctorDto.setSpecialization(docSpecType);
            registered = doctorsService.addDoctor(newDoctorDto);
            mav.addObject("addedDoctor", doctorsFinder.findByLogin(newDoctorDto.getLogin()));
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("bladRejestracji");
        } else {
            return mav;
        }
    }
}
