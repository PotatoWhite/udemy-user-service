package me.potato.userservice.controller;

import lombok.RequiredArgsConstructor;
import me.potato.userservice.dto.TransactionRequestDto;
import me.potato.userservice.dto.TransactionResponseDto;
import me.potato.userservice.entity.UserTransaction;
import me.potato.userservice.service.TransactionService;
import me.potato.userservice.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("user/transaction")
public class UserTransactionController {
    private final UserService        userService;
    private final TransactionService transactionService;

    @PostMapping
    public Mono<TransactionResponseDto> create(@Valid @RequestBody @NotNull Mono<TransactionRequestDto> dto) {
        return dto.flatMap(transactionService::createTransaction);
    }

    @GetMapping
    public Flux<UserTransaction> getAllByUserId(@RequestParam Long userId) {
        return transactionService.getAllByUserId(userId);
    }
}
