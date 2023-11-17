package com.devsu.account.controller;

import com.devsu.account.dto.ReportDto;
import com.devsu.account.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@RestController
@RequestMapping("api/reportes")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping
    Flux<ReportDto> retrieve(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam Long clientId) {
        return reportService.retrieveMovementsByClientIdAndDate(startDate, endDate, clientId);
    }

}
