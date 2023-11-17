package com.devsu.account.mapper;

import com.devsu.account.dto.AccountDto;
import com.devsu.account.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    Account toAccount(AccountDto accountDto);
}
