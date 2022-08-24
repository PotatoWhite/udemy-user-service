package me.potato.userservice.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@ToString
public class UserTransaction {
    @Id
    private Long          id;
    private Long          userId;
    private Long          amount;
    private LocalDateTime transactionDate;
}
