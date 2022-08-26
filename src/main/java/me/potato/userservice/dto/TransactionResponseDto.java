package me.potato.userservice.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class TransactionResponseDto {
    @NotNull
    private Long              userId;
    @NotNull
    private Integer           amount;
    @NotNull
    private TransactionStatus status;
}
