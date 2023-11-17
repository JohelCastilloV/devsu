package com.devsu.account.service;

import com.devsu.account.dto.ReportDto;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface ReportService {
    Flux<ReportDto> retrieveMovementsByClientIdAndDate(LocalDate startDate, LocalDate endDate, Long clientId);
}
