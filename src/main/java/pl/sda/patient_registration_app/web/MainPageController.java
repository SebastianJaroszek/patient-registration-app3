package pl.sda.patient_registration_app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainPageController {

    @GetMapping(value = "/main")
    public String mainPage() {
        return "main";
    }

    @PostMapping(value = "/main")
    public String mainPostPage() {
        return "main";
    }


}
