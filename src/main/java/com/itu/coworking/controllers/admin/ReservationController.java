package com.itu.coworking.controllers.admin;

import com.itu.coworking.repository.ReservationRepository;
import com.itu.coworking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReservationController {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReservationService reservationService;

    @GetMapping("/admin/reservation/import")
public  String reservationImportForm(){
    return "Admin/reservation/import";
}

@PostMapping("/admin/reservation/import")
public String reservationImport(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
    if (file.isEmpty()) {
        redirectAttributes.addFlashAttribute("message", "Veuillez sélectionner un fichier CSV.");
        return "redirect:/admin/reservation/import";
    }
    try {
        reservationService.importCsv(file);
        redirectAttributes.addFlashAttribute("success", "Importation réussie !");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Erreur lors de l'importation : " + e.getMessage());
    }
    return "redirect:/admin/reservation/import";
}

@GetMapping("/admin/reservations")
public String reservationList(Model model){
        model.addAttribute("reservations",reservationRepository.findAll());
    return "Admin/reservation/liste";
}

}
