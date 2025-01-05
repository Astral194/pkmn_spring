package ru.mirea.Pegov.pkmn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;
import ru.mirea.Pegov.pkmn.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 1. список из всех пользователей в базе.
    @GetMapping("/all")
    public List<StudentEntity> getAllUsers() {
        return userService.findAllStudents();
    }

    // 2. список пользователей из конкретной группы.
    @GetMapping("/{group}")
    public List<StudentEntity> getUsersByGroup(@PathVariable String group) {
        return userService.getStudentsByGroup(group);
    }

    // 3. JSON с ФИО и возвращает пользователя.
    @GetMapping("/fio")
    public Optional<StudentEntity> getUserByFullName(@RequestBody StudentEntity student) {
        return userService.getStudentByFIO(student);
    }

    // POST метод для создания пользователя
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody StudentEntity student) {
        if (userService.getStudentByFIO(student).isPresent()) {
            return ResponseEntity.badRequest().body("Ай ай ай такой студент уже есть!!!!");
        }

        return ResponseEntity.ok(userService.save(student).toString());
    }
}
