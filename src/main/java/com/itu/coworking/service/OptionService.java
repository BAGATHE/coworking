package com.itu.coworking.service;

import com.itu.coworking.model.Espace;
import com.itu.coworking.model.Option;
import com.itu.coworking.repository.OptionRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class OptionService {
@Autowired
OptionRepository optionRepository;
    public void importCsv(MultipartFile file) throws Exception {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Option> options = new ArrayList<>();

            for (CSVRecord record : csvParser) {
                String code = record.get("code");
                String option = record.get("option");
                double prix = Double.parseDouble(record.get("prix"));

                // Vérification du prix
                if (prix < 0) {
                    throw new IllegalArgumentException("Le prix  doit être  supérieur à 0. Erreur pour : " + code);
                }

                Option option1 = new Option();
                option1.setCode(code);
                option1.setOption(option);
                option1.setPrix(prix);
                options.add(option1);
            }

            optionRepository.saveAll(options);
        } catch (Exception e) {
            throw new Exception("Erreur lors de l'importation du fichier CSV : " + e.getMessage());
        }
    }


}
