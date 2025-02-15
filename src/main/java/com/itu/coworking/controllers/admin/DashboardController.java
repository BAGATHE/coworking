package com.itu.coworking.controllers.admin;

import com.itu.coworking.dto.ReservationCountByHeure;
import com.itu.coworking.model.Reservation;
import com.itu.coworking.model.Utilisateur;
import com.itu.coworking.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {
    @Autowired
    ReservationService reservationService;

    @GetMapping("/admin/dashboard")
    public String dashboardAdmin(Model model) {
        LocalDate today = LocalDate.now();
        String dateDebut = today.format(DateTimeFormatter.ISO_DATE);
        String dateFin = today.format(DateTimeFormatter.ISO_DATE);
        List<ReservationCountByHeure> stats = reservationService.getReservationCountByHeure();
        model.addAttribute("statsReservations", stats);
        model.addAttribute("chiffreaffaire",reservationService.getChiffreAffaire());
        model.addAttribute("chiffreaffaires", reservationService.getReservationsBetweenDates(dateDebut,dateFin));
        return "Admin/dashboard";
    }

    @PostMapping("/admin/dashboard")
    public String dashboardAdmin(Model model,@RequestParam("date_debut") String dateDebut, @RequestParam("date_fin") String dateFin) {
        List<ReservationCountByHeure> stats = reservationService.getReservationCountByHeure();
        List<Reservation> reservations = reservationService.getReservationsBetweenDates(dateDebut, dateFin);
        // Transformer les données en une liste de dates et une liste de totaux
        List<String> dates = new ArrayList<>();
        List<Double> totals = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Reservation reservation : reservations) {
            dates.add(dateFormat.format(reservation.getDateReservation()));
            totals.add(reservation.getTotal());
        }
        model.addAttribute("statsReservations", stats);
        model.addAttribute("chiffreaffaire",reservationService.getChiffreAffaire());
        model.addAttribute("chiffreaffaires", reservationService.getReservationsBetweenDates(dateDebut,dateFin));
        model.addAttribute("dates", dates); // Liste des dates formatées
        model.addAttribute("totals", totals);
        return "Admin/dashboard";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginAdmin";
    }

}