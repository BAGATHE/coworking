package com.itu.coworking.service;

import com.itu.coworking.model.Profil;
import com.itu.coworking.model.Utilisateur;
import com.itu.coworking.repository.ProfilRepository;
import com.itu.coworking.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
@Autowired
private UtilisateurRepository utilisateurRepository;
@Autowired
private ProfilRepository profilRepository;

    public Utilisateur findUtilisateurByTelephone(String numero) {
        Utilisateur utilisateur = utilisateurRepository.findByTelephone(numero);
        if (utilisateur == null) {
            utilisateur = new Utilisateur();
            utilisateur.setProfil(profilRepository.findByProfil("Client"));
            utilisateur.setTelephone(numero);
            utilisateur = utilisateurRepository.saveAndFlush(utilisateur);
        }
        return utilisateur;
    }


}
