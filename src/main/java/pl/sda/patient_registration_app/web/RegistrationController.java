package pl.sda.patient_registration_app.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.patient_registration_app.bo.DoctorDaysService;
import pl.sda.patient_registration_app.bo.UtilsService;
import pl.sda.patient_registration_app.bo.VisitsService;
import pl.sda.patient_registration_app.dto.MyUserPrincipalDto;
import pl.sda.patient_registration_app.dto.RegisterDto;
import pl.sda.patient_registration_app.type.DocSpecType;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Controller
public class RegistrationController {

    private final VisitsService visitsService;
    private final DoctorDaysService doctorDaysService;
    private final UtilsService utilsService;

    @Autowired
    public RegistrationController(VisitsService visitsService, DoctorDaysService doctorDaysService,
                                  UtilsService utilsService) {
        this.visitsService = visitsService;
        this.doctorDaysService = doctorDaysService;
        this.utilsService = utilsService;
    }

    @GetMapping("/rejestracja")
    public ModelAndView showRegistrationPage() {
        ModelAndView mav = new ModelAndView("rejestracja");
        mav.addObject("docSpecEnum", utilsService.convertSpecEnum());
        return mav;
    }

    @GetMapping("/rejestracja/specjalista")
    public ModelAndView showDoctorsSchedule(@RequestParam(name = "specType", required = false) String specName,
                                            @RequestParam(name = "date", required = false)
                                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        ModelAndView mav = new ModelAndView("tabelaWizyt");

        if (date == null) {
            date = LocalDate.now();
        }

        DocSpecType docSpecType = DocSpecType.findByName(specName);

        mav.addObject("doctorDayDtoList",
                doctorDaysService.createDayDtoFromDoctorDtoAndDate(date, docSpecType));
        mav.addObject("hours", utilsService.getHours());
        mav.addObject("dateOfVisits", date);
        mav.addObject("weekDayName", date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));
        mav.addObject("registerDto", new RegisterDto());
        mav.addObject("specType", specName);
        return mav;
    }

    @PostMapping("/rejestracja/specjalista")
    public ModelAndView registerPatientToVisit(@ModelAttribute("registerDto") RegisterDto registerDto) {
        ModelAndView mav = new ModelAndView("podsumowanieRejestracji");

        MyUserPrincipalDto myUserPrincipalDto = (MyUserPrincipalDto) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        visitsService.registerPatient(registerDto, myUserPrincipalDto.getId());

        return mav;
    }
}
