package tech.itpark.itparkfinalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tech.itpark.itparkfinalproject.dto.security.UserDto;
import tech.itpark.itparkfinalproject.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        return "registration/registrationPage";
    }


    @PostMapping("/registration/save")
    public String save(@Valid UserDto userDto,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(userDto);
            model.addAttribute("errors", bindingResult.getFieldErrors());
            return "registration/registrationPage";
        }
        userService.save(userDto);
        return "redirect:/";
    }

}
