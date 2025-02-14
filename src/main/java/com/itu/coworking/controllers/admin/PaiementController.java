package com.itu.coworking.controllers.admin;

import com.itu.coworking.repository.PaiementRepository;
import com.itu.coworking.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PaiementController {
    @Autowired
    PaiementService paiementService;

    @Autowired
    PaiementRepository paiementRepository;

    @GetMapping("/admin/paiement/import")
    public String paiementImportForm(){
    return "Admin/paiement/import";
    }

    @PostMapping("/admin/paiement/import")
    public String paiementImport(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
    if(file.isEmpty()){
        redirectAttributes.addFlashAttribute("message","veuillez selectionner un fichier csv");
        return "redirect:/admin/paiement/import";
    }
    try{
        paiementService.importCsv(file);
        redirectAttributes.addFlashAttribute("success","importation reussi ");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Erreur lors de l'importation : " + e.getMessage());
    }

    return "redirect:/admin/paiement/import";
    }

    @GetMapping("/admin/paiements")
    public String paiementList(Model model){
        model.addAttribute("paiements",paiementRepository.findAll());
        return "Admin/paiement/liste";
    }
}
