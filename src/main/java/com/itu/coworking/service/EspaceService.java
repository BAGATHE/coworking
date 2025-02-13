package com.itu.coworking.service;

import com.itu.coworking.model.Espace;
import com.itu.coworking.repository.EspaceRepository;
import org.apache.commons.csv.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class EspaceService {
    @Autowired
    private EspaceRepository espaceRepository;

    public void importCsv(MultipartFile file) throws Exception {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Espace> espaces = new ArrayList<>();

            for (CSVRecord record : csvParser) {
                String nom = record.get("nom");
                double prixHeure = Double.parseDouble(record.get("prix_heure"));

                // Vérification du prix_heure
                if (prixHeure < 0) {
                    throw new IllegalArgumentException("Le prix par heure doit être  supérieur à 0. Erreur pour : " + nom);
                }

                Espace espace = new Espace();
                espace.setNom(nom);
                espace.setPrixHeure(prixHeure);

                espaces.add(espace);
            }

            espaceRepository.saveAll(espaces);
        } catch (Exception e) {
            throw new Exception("Erreur lors de l'importation du fichier CSV : " + e.getMessage());
        }
    }

    public Espace findEspaceByNom(String nom){
        return  espaceRepository.findByNom(nom);
    }
}
