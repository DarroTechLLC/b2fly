package darrotech.project.b2fly.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")  // Root mapping
    public String home() {
        return "index"; // Serve index.html for the root URL
    }
}

