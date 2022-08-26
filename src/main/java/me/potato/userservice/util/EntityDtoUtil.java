package me.potato.userservice.util;

import me.potato.userservice.dto.TransactionRequestDto;
import me.potato.userservice.dto.TransactionResponseDto;
import me.potato.userservice.dto.TransactionStatus;
import me.potato.userservice.dto.UserDto;
import me.potato.userservice.entity.User;
import me.potato.userservice.entity.UserTransaction;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class EntityDtoUtil {
    public static UserDto toDto(User user) {
        var dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    public static User toEntity(UserDto dto) {
        var user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }

    public static UserTransaction toEntity(TransactionRequestDto dto) {
        var userTransaction = new UserTransaction();
        userTransaction.setUserId(dto.getUserId());
        userTransaction.setAmount(dto.getAmount());
        userTransaction.setTransactionDate(LocalDateTime.now());
        return userTransaction;
    }

    public static TransactionResponseDto toDto(TransactionRequestDto requestDto, TransactionStatus status) {
        var responseDto = new TransactionResponseDto();
        responseDto.setUserId(requestDto.getUserId());
        responseDto.setAmount(requestDto.getAmount());
        responseDto.setStatus(status);
        return responseDto;
    }

    public static TransactionResponseDto toDto(UserTransaction userTransaction, TransactionStatus status) {
        var responseDto = new TransactionResponseDto();
        responseDto.setUserId(userTransaction.getUserId());
        responseDto.setAmount(userTransaction.getAmount());
        responseDto.setStatus(status);
        return responseDto;
    }
}
