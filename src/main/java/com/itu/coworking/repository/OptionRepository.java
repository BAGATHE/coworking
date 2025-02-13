package com.itu.coworking.repository;

import com.itu.coworking.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option,Integer> {
 Option findByOption(String option);
 @Query(value = "SELECT * FROM option WHERE code = :code", nativeQuery = true)
 Option findByCode(@Param("code") String code);

}
