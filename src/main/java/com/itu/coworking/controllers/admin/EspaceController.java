package com.itu.coworking.controllers.admin;

import com.itu.coworking.model.Utilisateur;
import com.itu.coworking.repository.EspaceRepository;
import com.itu.coworking.service.EspaceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EspaceController {
    @Autowired
    private EspaceService espaceService;

    @Autowired
    private EspaceRepository espaceRepository;

@GetMapping("/admin/espace/import")
public String importEspaceForm(){
    return "Admin/espace/import";
}

    @PostMapping("/admin/espace/import")
    public String importEspace(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Veuillez sélectionner un fichier CSV.");
            return "redirect:/admin/espace/import";
        }
        try {
            espaceService.importCsv(file);
            redirectAttributes.addFlashAttribute("success", "Importation réussie !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'importation : " + e.getMessage());
        }

        return "redirect:/admin/espace/import";
    }


    @GetMapping("/admin/espace/list")
    public String espaceList(Model model){
       model.addAttribute("espaces", espaceRepository.findAll());
       return "Admin/espace/liste";
    }
}
