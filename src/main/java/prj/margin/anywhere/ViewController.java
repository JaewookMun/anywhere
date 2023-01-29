package prj.margin.anywhere;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {


    @GetMapping("greeting")
    public String greeting(@RequestParam(name = "temp", required = false, defaultValue = "wow") String name, Model model) {
        model.addAttribute("attr", name);

        return "greeting";
    }
}
