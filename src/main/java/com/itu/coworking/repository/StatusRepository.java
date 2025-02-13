package com.itu.coworking.repository;

import com.itu.coworking.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository  extends JpaRepository<Status,Integer> {
Boolean existsByStatus(String status);
Status findByCode(String code);
}
