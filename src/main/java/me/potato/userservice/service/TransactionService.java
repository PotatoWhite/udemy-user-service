package me.potato.userservice.service;

import lombok.RequiredArgsConstructor;
import me.potato.userservice.dto.TransactionRequestDto;
import me.potato.userservice.dto.TransactionResponseDto;
import me.potato.userservice.dto.TransactionStatus;
import me.potato.userservice.entity.UserTransaction;
import me.potato.userservice.repository.UserRepository;
import me.potato.userservice.repository.UserTransactionRepository;
import me.potato.userservice.util.EntityDtoUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final UserRepository userRepository;

    private final UserTransactionRepository userTransactionRepository;

    public Mono<TransactionResponseDto> createTransaction(TransactionRequestDto dto) {
        return userRepository.updateUserBalance(dto.getUserId(), dto.getAmount())
                .filter(Boolean::booleanValue)
                .map(b -> EntityDtoUtil.toEntity(dto))
                .flatMap(userTransactionRepository::save)
                .map(e -> EntityDtoUtil.toDto(dto, TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDtoUtil.toDto(dto, TransactionStatus.DECLINED));
    }

    public Flux<UserTransaction> getAllByUserId(Long userId) {
        return userTransactionRepository.findAllByUserId(userId);


    }
}
