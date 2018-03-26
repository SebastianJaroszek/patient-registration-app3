package pl.sda.patient_registration_app.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.patient_registration_app.bo.*;
import pl.sda.patient_registration_app.dto.DoctorDto;
import pl.sda.patient_registration_app.dto.MyUserPrincipalDto;
import pl.sda.patient_registration_app.dto.RegisterDto;
import pl.sda.patient_registration_app.dto.VisitDto;
import pl.sda.patient_registration_app.type.DocSpecType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class RegistrationController {

    private final RegistrationFinder registrationFinder;
    private final RegistrationService registrationService;
    private final VisitsService visitsService;
    private final DoctorDaysService doctorDaysService;
    private final UtilsService utilsService;
    private final TimetablesService timetablesService;

    @Autowired
    public RegistrationController(RegistrationFinder registrationFinder, RegistrationService registrationService,
                                  VisitsService visitsService, DoctorDaysService doctorDaysService,
                                  UtilsService utilsService, TimetablesService timetablesService) {
        this.registrationFinder = registrationFinder;
        this.registrationService = registrationService;
        this.visitsService = visitsService;
        this.doctorDaysService = doctorDaysService;
        this.utilsService = utilsService;
        this.timetablesService = timetablesService;
    }

    @GetMapping("/rejestracja")
    public ModelAndView showRegistrationPage() {

        //timetablesService.addTimetablesToDB();

        ModelAndView mav = new ModelAndView("rejestracja");

        //mav.addObject("visits", registrationFinder.showAllVisits());
        mav.addObject("docSpecEnum", utilsService.convertSpecEnum());
        return mav;
    }

    @GetMapping("/rejestracja/specjalista")
    public ModelAndView showDoctorsSchedule(@RequestParam(name = "specType", required = false) String specName,
                                            @RequestParam(name = "date", required = false)
                                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        ModelAndView mav = new ModelAndView("tabelaWizyt");
        // dodać filtrowanie po specjalizacji
        if (date == null) {
            date = LocalDate.now();
        } /*else {
            date = date.plusDays(1);
        }*/

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

    @PostMapping("/rejestracja/specjalista")/*/rejestracja/specjalista?visitId=3*/
    public ModelAndView registerPatientToVisit(@ModelAttribute("registerDto") RegisterDto registerDto) {
        ModelAndView mav = new ModelAndView("podsumowanieRejestracji");
        /*DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(Long.valueOf(5));
        visitDto.setDoctor(doctorDto);*/ //TODO CASCADE
        //visitDto.setDayOfVisit(LocalDate.of(2018, 6, 10));
        /*RegisterDto registerDto = RegisterDto.builder()
                .time(time)
                .date(date)
                .doctorId(doctorId)
                .build();*/
        MyUserPrincipalDto myUserPrincipalDto = (MyUserPrincipalDto) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        visitsService.registerPatient(registerDto, myUserPrincipalDto.getId());
        //doctorDaysService.createDayDtoFromDoctorDtoAndDate(registerDto.getDate());
        //mav.addObject("id", visitId);

        return mav;
    }
}
