package com.itu.coworking.service;

import com.itu.coworking.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class StatusInitializer implements CommandLineRunner {

    private  final StatusRepository statusRepository;

    public StatusInitializer(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        /*
    if(!statusRepository.existsByStatus("fait")) {
        statusRepository.save(new com.itu.coworking.model.Status("fait","1"));
    }
    if(!statusRepository.existsByStatus("a payer")) {
        statusRepository.save(new com.itu.coworking.model.Status("a payer","2"));
    }
    if(!statusRepository.existsByStatus("valider")) {
        statusRepository.save(new com.itu.coworking.model.Status("valider","3"));
    }
    if(!statusRepository.existsByStatus("payer")) {
        statusRepository.save(new com.itu.coworking.model.Status("payer","4"));
    }

         */
    }
}
