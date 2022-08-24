package me.potato.userservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransactionRequestDto {
    private Long userId;
    private Long amount;
}
