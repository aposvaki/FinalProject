package gr.aueb.cf.fnlprojecttecheshop.controller;

import gr.aueb.cf.fnlprojecttecheshop.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final IUserService userService;

    @GetMapping("/login")
    public String login(Model model, Principal principal, HttpServletRequest request) {
        if (principal == null) return "login";

        var user = userService.getUserByUsername(principal.getName());
        String role = user.getRole().name();

        if (role.equals("ADMINAS")) {
            return "redirect:/admin/dashboard";
        }

        if (role.equals("CUSTOMER")) {
            return "redirect:/";
        }

        return "redirect:/dashboard";
    }

    @GetMapping(path = { "/" })
    String root(Model model, Principal principal, HttpServletRequest request) throws Exception{
        return "redirect:/login";
    }

}
