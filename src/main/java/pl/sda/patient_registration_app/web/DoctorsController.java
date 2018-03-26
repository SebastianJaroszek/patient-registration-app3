package pl.sda.patient_registration_app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.patient_registration_app.bo.*;
import pl.sda.patient_registration_app.dto.DoctorDto;
import pl.sda.patient_registration_app.type.DocSpecType;

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
        model.addAttribute("newDoctor", new DoctorDto());
        model.addAttribute("docSpecEnum", utilsService.convertSpecEnum());
        return "dodawanieLekarza";
    }

    @PostMapping("/dodajLekarza")
    public ModelAndView addDoctorToDB(@ModelAttribute("newDoctor") DoctorDto doctorDto,
                                      @RequestParam("specType") String specType) {
        DocSpecType docSpecType = DocSpecType.findByName(specType);
        doctorDto.setSpecialization(docSpecType);
        doctorsService.addDoctor(doctorDto);
        ModelAndView mav = new ModelAndView("dodawanieLekarzaWynik");
        mav.addObject("addedDoctor", doctorsFinder.findByLogin(doctorDto.getLogin()));
        return mav;
    }
}
