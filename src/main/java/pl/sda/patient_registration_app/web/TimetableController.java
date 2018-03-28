package pl.sda.patient_registration_app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.patient_registration_app.bo.DoctorsService;
import pl.sda.patient_registration_app.bo.TimetablesService;
import pl.sda.patient_registration_app.bo.UtilsService;
import pl.sda.patient_registration_app.dto.NewTimetableDto;

import java.time.DayOfWeek;
import java.util.List;

@Controller
public class TimetableController {

    private final DoctorsService doctorsService;
    private final UtilsService utilsService;
    private final TimetablesService timetablesService;

    @Autowired
    public TimetableController(DoctorsService doctorsService, UtilsService utilsService, TimetablesService timetablesService) {
        this.doctorsService = doctorsService;
        this.utilsService = utilsService;
        this.timetablesService = timetablesService;
    }

    @GetMapping("/dodawanieHarmonogramu")
    public ModelAndView showAddTimetablePage(@ModelAttribute("newTimetable") NewTimetableDto newTimetableDto) {
        ModelAndView mav = new ModelAndView("dodawanieHarmonogramuFormularz");
        List<Long> checkedDoctorsId = doctorsService.getCheckedDoctorsId(newTimetableDto);
        mav.addObject("newTimetable", new NewTimetableDto());
        mav.addObject("doctorsId", checkedDoctorsId);
        mav.addObject("daysOfWeek", DayOfWeek.values());
        mav.addObject("openHours", utilsService.getClinicHoursOfWork());
        return mav;
    }

    @PostMapping("/dodawanieHarmonogramu")
    public ModelAndView addNewTimetable(@ModelAttribute("newTimetable") NewTimetableDto newTimetableDto) {
        ModelAndView mav = new ModelAndView("dodawanieHarmonogramuWynik");
        timetablesService.addNewTimetableToDB(newTimetableDto);
        return mav;
    }

}
