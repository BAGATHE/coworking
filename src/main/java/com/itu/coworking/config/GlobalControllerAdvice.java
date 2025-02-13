package com.itu.coworking.config;

import com.itu.coworking.model.Utilisateur;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addUserToModel(Model model, HttpSession session) {
        Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");

        if (utilisateurConnecte != null) {
            model.addAttribute("utilisateur", utilisateurConnecte);
        }
    }
}
