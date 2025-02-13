package com.itu.coworking.controllers.admin;

import com.itu.coworking.repository.OptionRepository;
import com.itu.coworking.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OptionController {
    @Autowired
    OptionService optionService;
    @Autowired
    OptionRepository optionRepository;

    @GetMapping("admin/option/import")
public  String optionImportForm(){
    return "Admin/option/import";
}
@PostMapping("admin/option/import")
public String optionImport(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
    if (file.isEmpty()) {
        redirectAttributes.addFlashAttribute("message", "Veuillez sélectionner un fichier CSV.");
        return "redirect:/admin/option/import";
    }
    try {
        optionService.importCsv(file);
        redirectAttributes.addFlashAttribute("success", "Importation réussie !");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Erreur lors de l'importation : " + e.getMessage());
    }

    return "redirect:/admin/option/import";
}
@GetMapping("/admin/options")
public String optionList(Model model){
        model.addAttribute("options",optionRepository.findAll());
    return "Admin/option/liste";
}
}
