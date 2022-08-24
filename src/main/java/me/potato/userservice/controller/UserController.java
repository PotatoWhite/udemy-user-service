package me.potato.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.potato.userservice.dto.UserDto;
import me.potato.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public Flux<UserDto> getAllUsers() {
        return userService.all();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<UserDto>> getUserByIdOrNotfound(@PathVariable Long id) {
        return userService.getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<UserDto> createUser(@RequestBody Mono<UserDto> dto) {
        return userService.createUser(dto);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<UserDto>> changeUserOrNotFound(@PathVariable Long id, @RequestBody Mono<UserDto> dto) {
        return userService.changeUser(id, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PatchMapping("{id}")
    public Mono<ResponseEntity<UserDto>> updateUserOrNotFound(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return userService.updateUser(id, fields)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteUserOrNotFound(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
