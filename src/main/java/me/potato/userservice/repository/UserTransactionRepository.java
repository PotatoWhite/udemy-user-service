package me.potato.userservice.repository;

import me.potato.userservice.entity.UserTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction, Long> {
}
