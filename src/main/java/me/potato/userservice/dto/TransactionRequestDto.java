package me.potato.userservice.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class TransactionRequestDto {
    @NotNull
    private Long    userId;
    private Integer amount;
}
