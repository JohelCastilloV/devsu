package com.devsu.account.mapper;

import com.devsu.account.dto.MovementDto;
import com.devsu.account.dto.ReportDto;
import com.devsu.account.model.Client;
import com.devsu.account.model.Movement;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovementMapper {
    @Mapping(source = "accountId", target = "account.id")
    Movement toMovement(MovementDto movementDto);

    @Mapping(source = "movement.account.accountNumber", target = "accountNumber")
    @Mapping(source = "movement.account.accountType", target = "accountType")
    @Mapping(source = "client.name", target = "name")
    @Mapping(source = "movement.amount", target = "amount")
    @Mapping(source = "movement.account.status", target = "status")
    @Mapping(source = "movement.createdDate", target = "createdDate")
    @Mapping(source = "movement.previousBalance", target = "previousBalance")
    ReportDto toReportDto(Movement movement, Client client);
    @AfterMapping
    default void setMovementTypeAndDate(MovementDto movementDto, @MappingTarget Movement movement) {
        movement.setMovementType(BigDecimal.ZERO.compareTo(movement.getAmount())>0 ? "retiro": "deposito");
        movement.setCreatedDate(LocalDate.now());
    }
}
