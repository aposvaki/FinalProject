package gr.aueb.cf.fnlprojecttecheshop.controller;

import gr.aueb.cf.fnlprojecttecheshop.dto.UserInsertDTO;
import gr.aueb.cf.fnlprojecttecheshop.model.User;
import gr.aueb.cf.fnlprojecttecheshop.service.IUserService;
import gr.aueb.cf.fnlprojecttecheshop.service.exceptions.UserAlreadyExistsException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegisterUserController {

    private final IUserService userService;

    @GetMapping("/register")
    public String login(Model model) {
        model.addAttribute("userForm", new UserInsertDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("userForm") UserInsertDTO userInsertDTO, BindingResult bindingResult) throws UserAlreadyExistsException {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            User createUser = userService.insertUser(userInsertDTO);
        } catch (UserAlreadyExistsException e) {
            throw e;
        }

        return "redirect:/login";
    }
}
