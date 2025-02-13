package com.itu.coworking.controllers.admin;

import com.itu.coworking.model.Utilisateur;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class dashboardController {
    @GetMapping("/admin/dashboard")
    public String dashboardAdmin(HttpSession session, Model model) {
        Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");

        if (utilisateurConnecte == null) {
            //
            return "redirect:/admin/login";
        }

        model.addAttribute("utilisateur", utilisateurConnecte);

        return "Admin/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginAdmin";
    }

}