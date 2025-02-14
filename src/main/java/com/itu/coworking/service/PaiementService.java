package com.itu.coworking.service;

import com.itu.coworking.model.*;
import com.itu.coworking.repository.PaiementRepository;
import com.itu.coworking.repository.ReservationRepository;
import com.itu.coworking.repository.StatusRepository;
import com.itu.coworking.repository.StatusReservationRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaiementService {
@Autowired
private PaiementRepository paiementRepository;

@Autowired
private ReservationRepository reservationRepository;

@Autowired
private StatusRepository statusRepository;

@Autowired
private StatusReservationRepository statusReservationRepository;

public void importCsv(MultipartFile file) throws Exception {
    Status statusapayer = statusRepository.findByCode("2");
    Status statusvalide = statusRepository.findByCode("3");
    Status statuspayer = statusRepository.findByCode("4");


    try(BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
    CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
        for (var record : csvParser) {
            String ref_paiement = record.get("ref_paiement");
            String ref_reservation = record.get("ref");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date_paiement = new Date(dateFormat.parse(record.get("date")).getTime());
            Reservation reservation = reservationRepository.findReservationByReference(ref_reservation);

            Paiement paiement = new Paiement();
            paiement.setDatePaiement(date_paiement);
            paiement.setRefPaiement(ref_paiement);
            paiement.setReservation(reservation);
            paiementRepository.save(paiement);
            statusReservationRepository.save(new StatusReservation(reservation,statusapayer, LocalDateTime.now()));
            statusReservationRepository.save(new StatusReservation(reservation,statusvalide, LocalDateTime.now()));
            statusReservationRepository.save(new StatusReservation(reservation,statuspayer, LocalDateTime.now()));

            double montant = 0;
            List<OptionReservation> optionReservations = reservation.getOptionReservations();
            for(OptionReservation optionReservation : optionReservations){
                montant += optionReservation.getOption().getPrix();
            }
            reservation.setTotal(montant);
            reservationRepository.save(reservation);
        }
    } catch (Exception e) {
        throw new Exception("Erreur lors de l'importation du fichier CSV : " + e.getMessage());
    }
}

}
