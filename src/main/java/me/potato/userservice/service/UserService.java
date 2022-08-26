package me.potato.userservice.service;

import lombok.RequiredArgsConstructor;
import me.potato.userservice.dto.UserDto;
import me.potato.userservice.repository.UserRepository;
import me.potato.userservice.util.EntityDtoUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public Flux<UserDto> all() {
        return userRepository.findAll().map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> getById(Long id) {
        return userRepository.findById(id).map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> createUser(Mono<UserDto> dto) {
        return dto
                .map(EntityDtoUtil::toEntity)
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> changeUser(Long id, Mono<UserDto> dto) {
        return userRepository.findById(id)
                .flatMap(user -> dto.map(EntityDtoUtil::toEntity)
                        .doOnNext(e -> e.setId(user.getId())))
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> updateUser(Long id, Map<String, Object> fields) {
        return userRepository.findById(id)
                .doOnNext(user ->
                        fields.forEach((key, value) -> {
                            if (key.equals("name"))
                                user.setName((String) value);
                            else if (key.equals("balance"))
                                user.setBalance((Integer) value);
                        }))
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteUser(Long id) {
        return userRepository.deleteById(id);
    }
}
