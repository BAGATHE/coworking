package com.itu.coworking.service;

import com.itu.coworking.dto.ChiffreAffaire;
import com.itu.coworking.dto.ReservationCountByHeure;
import com.itu.coworking.model.*;
import com.itu.coworking.repository.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private EspaceService espaceService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private OptionReservationRepository optionReservationRepository;
    @Autowired
    private StatusReservationRepository statusReservationRepository;
    @Autowired
    private StatusRepository statusRepository;

    public static String[] splitTextBySeparator(String text, String separator) {
        return Arrays.stream(text.split(separator))
                .map(String::trim)
                .toArray(String[]::new);
    }


    private Reservation generateInstanceReservation(CSVRecord record) throws ParseException {
        String ref = record.get("ref");
        String nomEspace = record.get("espace");
        String telephone = record.get("client");
        String dateStr = record.get("date");
        String heureDebutStr = record.get("heure_debut");
        Integer duree = Integer.parseInt(record.get("duree"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(dateFormat.parse(dateStr).getTime());
        if (!heureDebutStr.contains(":")) {
            throw new ParseException("Format d'heure invalide: " + heureDebutStr, 0);
        }
        if (heureDebutStr.length() == 5) {
            heureDebutStr += ":00";
        }
        Time heureDebut = Time.valueOf(heureDebutStr);

        Utilisateur client = utilisateurService.findUtilisateurByTelephone(telephone);;
        Espace espace = espaceService.findEspaceByNom(nomEspace);
        Reservation reservation = new Reservation();
        reservation.setReference(ref);
        reservation.setEspace(espace);
        reservation.setUtilisateur(client);
        reservation.setHeureDebut(heureDebut);
        reservation.setDateReservation(date);
        reservation.setDuree(duree);
        reservation.setTotal(0);

        return reservation;
    }





    public void importCsv(MultipartFile file) throws Exception {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            Status status = statusRepository.findByCode("1");

            for (CSVRecord record : csvParser) {
                Reservation reservation = generateInstanceReservation(record);

                String options = record.get("option");

                String[] tab_options = splitTextBySeparator(options,",");
                System.out.println("taille tab" + tab_options.length);
                Reservation reservation1 = reservationRepository.saveAndFlush(reservation);

                for(int i=0;i<tab_options.length;i++){
                    optionReservationRepository.save(new OptionReservation(reservation1,optionRepository.findByCode(tab_options[i].toUpperCase())));
                }
                /*je fait update du prix total*/
                Reservation reservation2 = reservationRepository.findReservationByReference(reservation1.getReference());
                double montant = 0;
                List<OptionReservation> optionReservations = reservation2.getOptionReservations();
                for(OptionReservation optionReservation : optionReservations){
                    montant += optionReservation.getOption().getPrix();
                }
                reservation2.setTotal(montant);
                reservationRepository.save(reservation2);
                /***/
                statusReservationRepository.save(new StatusReservation(reservation1,status, LocalDateTime.now()));

            }

        } catch (Exception e) {
            throw new Exception("Erreur lors de l'importation du fichier CSV : " + e.getMessage());
        }
    }


    public List<ReservationCountByHeure> getReservationCountByHeure() {
        return reservationRepository.countReservationsByHeureDebut();
    }

    public ChiffreAffaire getChiffreAffaire(){
        List<Reservation> reservations =  reservationRepository.findAll();
        double montantpayer = 0;
        double montantnonpayer = 0;
        for(Reservation reservation : reservations){
            if (reservation.getPaiements().size() > 0){
                montantpayer += reservation.getTotal();
            }else{
                montantnonpayer += reservation.getTotal();
            }
        }
        ChiffreAffaire chiffreAffaire = new ChiffreAffaire(montantpayer,montantnonpayer);
        return chiffreAffaire;
    }
    public List<Reservation> getReservationsBetweenDates(String strdateDebut, String strdateFin) {
        Date dateDebut = Date.valueOf(strdateDebut);
        Date dateFin = Date.valueOf(strdateFin);
        return reservationRepository.findReservationsBetweenDates(dateDebut, dateFin);
    }
}
