package me.potato.userservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransactionResponseDto {
    private Long              userId;
    private Long              amount;
    private TransactionStatus status;
}
