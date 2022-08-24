package me.potato.userservice.repository;

import me.potato.userservice.entity.User;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    @Modifying
    @Query("update users set balance = balance - :amount where id = :userId and balance >= :amount")
    Mono<Boolean> updateUserBalance(long userId, long amount);
}
