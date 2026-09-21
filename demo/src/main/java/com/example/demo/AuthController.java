package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final Map<String, String> users = new HashMap<>();

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        if (fullName == null || fullName.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Please fill in all fields.");
            return "signup";
        }

        users.put(email, password);
        return "redirect:/dashboard";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        if (users.containsKey(email) && users.get(email).equals(password)) {
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid email or password.");
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
