package com.Tutienda.controller.web;

import com.Tutienda.entity.users.Role;
import com.Tutienda.entity.users.RoleEnum;
import com.Tutienda.entity.users.User;
import com.Tutienda.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Controller
public class AuthController {
    private final IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/iniciar-sesion")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model, Principal principal, RedirectAttributes flash) {

        if(principal!=null){
            flash.addFlashAttribute("info","Ya ha iniciado sesión antes");
            return "redirect:/";
        }
        if(error != null){
            model.addAttribute("error","Nombre de usuario o la contraseña incorrecta");
        }
        if(logout!=null){
            model.addAttribute("success","sesión cerrada");
        }
        return "users/login";
    }


    @GetMapping("/registro")
    public String getForm( Model model, RedirectAttributes flash){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            flash.addFlashAttribute("errorMessage","El usuario ya ha iniciado sesion");
            return "redirect:/tienda";
        }
        model.addAttribute("user",new User());
        return "users/register";
    }
    @PostMapping("/registro")
    public String register( @ModelAttribute("user") @Valid User user,BindingResult result, Model model, RedirectAttributes flash )  {
        if (result.hasErrors()) {
            return "users/register";
        }
        userService.createUser(user);
        return "redirect:/iniciar-sesion";
    }


}
