package com.devsu.account.repository;

import com.devsu.account.model.Movement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovementRepository extends CrudRepository<Movement, Long> {

    List<Movement> findByAccount_ClientIdAndCreatedDateBetween(Long clientId, LocalDate startDate, LocalDate endDate);
}
