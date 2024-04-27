package com.Tutienda.controller;

import com.Tutienda.entity.usuarios.Role;
import com.Tutienda.entity.usuarios.RoleEnum;
import com.Tutienda.entity.usuarios.User;
import com.Tutienda.service.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.HashSet;

@Controller
public class AuthController {
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(IUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model, Principal principal, RedirectAttributes flash) {
        if(principal!=null){
            flash.addFlashAttribute("info","Ya ha iniciado sesión antes");
            return "redirect:/index";
        }
        if(error != null){
            model.addAttribute("error","Nombre de usuario o la contraseña incorrecta");
        }
        if(logout!=null){
            model.addAttribute("success","sesión cerrada");
        }
        return "login";
    }


    @GetMapping("/register")
    public String getForm(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            model.addAttribute("errorMessage","El usuario ya ha iniciado sesión");
            return "redirect:/index";
        }
        model.addAttribute("user",new User());
        return "register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {
        try {
            Role role1 = new Role(RoleEnum.ADMIN);
            Role role2 = new Role(RoleEnum.USER);
            role1.setUser(user);
            role2.setUser(user);
            user.setRoles(new HashSet<Role>(){{add(role1);add(role2);}});
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.createUser(user);
        } catch (Exception e) {
            model.addAttribute("errorMessage",e.getMessage());
            System.out.println(e.getMessage());
            return "register";
        }
        return "redirect:/login";
    }
}
