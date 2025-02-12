package com.itu.coworking.service;

import com.itu.coworking.model.Profil;
import com.itu.coworking.model.Utilisateur;
import com.itu.coworking.repository.ProfilRepository;
import com.itu.coworking.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;

public class UtilisateurInitializer implements CommandLineRunner {
    private final ProfilRepository profilRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoderService passwordEncoderService;

    public UtilisateurInitializer(ProfilRepository profilRepository, UtilisateurRepository utilisateurRepository, PasswordEncoderService passwordEncoderService) {
        this.profilRepository = profilRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoderService = passwordEncoderService;
    }
    @Override
    public void run(String... args) throws Exception {
        Profil administrateur = profilRepository.findByProfil("Administrateur");
        if (administrateur == null) {
            administrateur = new Profil("Administrateur");
            profilRepository.save(administrateur);
        }

        String adminPassword = passwordEncoderService.encodePassword("admin");

        Utilisateur admin = new Utilisateur(
                null,
                adminPassword,
                "admin@gmail.com",
                administrateur
        );

        // Sauvegarder l'utilisateur
        utilisateurRepository.save(admin);

        System.out.println("Utilisateur administrateur créé avec succès !");
    }
}
