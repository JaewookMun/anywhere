package prj.margin.anywhere.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @GetMapping("/")
    public String home(Authentication auth, Model model) {
        if(Optional.ofNullable(auth).isEmpty()) return "redirect:/login";

        model.addAttribute("attr", LocalDateTime.now());

        return "home";
    }
    @GetMapping("/login")
    public String login(Authentication authentication, Model model,
                        @RequestParam(name = "error", required = false) String error,
                        @RequestParam(name = "exception", required = false) String exception) {
        if(Optional.ofNullable(authentication).isPresent() && authentication.isAuthenticated()) return "redirect:/";

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "login";
    }
}
