package com.itu.coworking.service;

import com.itu.coworking.model.Profil;
import com.itu.coworking.repository.ProfilRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class ProfilInitializer implements CommandLineRunner {

    private final ProfilRepository profilRepository;

    public ProfilInitializer(ProfilRepository profilRepository) {
        this.profilRepository = profilRepository;
    }
    @Override
    public void run(String... args) throws Exception {
      /*
        // Vérifie si les profils existent déjà
        if (!profilRepository.existsByProfil("Administrateur")) {
            profilRepository.save(new Profil("Administrateur"));
        }
        if (!profilRepository.existsByProfil("Client")) {
            profilRepository.save(new Profil("Client"));
        }

       */
    }
}
