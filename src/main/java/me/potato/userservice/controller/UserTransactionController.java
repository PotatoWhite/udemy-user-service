package me.potato.userservice.controller;

import lombok.RequiredArgsConstructor;
import me.potato.userservice.dto.TransactionRequestDto;
import me.potato.userservice.dto.TransactionResponseDto;
import me.potato.userservice.service.TransactionService;
import me.potato.userservice.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("user/transactions")
public class UserTransactionController {
    private final UserService        userService;
    private final TransactionService transactionService;

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> dto) {
        return dto.flatMap(transactionService::createTransaction);
    }
}
