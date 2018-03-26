package pl.sda.patient_registration_app.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
