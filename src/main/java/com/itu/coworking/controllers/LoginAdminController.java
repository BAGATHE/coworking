package com.itu.coworking.controllers;

import com.itu.coworking.model.Profil;
import com.itu.coworking.model.Utilisateur;
import com.itu.coworking.repository.UtilisateurRepository;
import com.itu.coworking.service.PasswordEncoderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginAdminController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private PasswordEncoderService passwordEncoderService;

    @GetMapping("/admin/login")
    public String loginAdmin(){
        return "Admin/login";
    }
    @PostMapping("/admin/login")
    public String loginAdminPost(@ModelAttribute("email") String email, @ModelAttribute("motdepasse") String motdepasse, RedirectAttributes redirectAttributes,
                                 HttpSession session){
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if(utilisateur!= null && utilisateur.getProfil().getProfil().equals("Administrateur")){
            if(passwordEncoderService.matchesPassword(motdepasse,utilisateur.getMotDePasse())){
                session.setAttribute("utilisateur", utilisateur);
                return "redirect:/admin/dashboard";
            }
        }
        redirectAttributes.addFlashAttribute("message", "Email ou mot de passe incorrect");
        return "redirect:/admin/login";
    }
}
