package ru.mirea.Pegov.pkmn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;
import ru.mirea.Pegov.pkmn.models.Student;
import ru.mirea.Pegov.pkmn.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<StudentEntity> getAllUsers() {
        return userService.findAllStudents();
    }


    @GetMapping("/{group}")
    public List<StudentEntity> getUsersByGroup(@PathVariable String group) {
        return userService.getStudentsByGroup(group);
    }


    @GetMapping("/fio")
    public Optional<StudentEntity> getUserByFullName(@RequestBody Student student) {
        return userService.getStudentByFIO(StudentEntity.fromStudentToEntity(student));
    }


    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody Student student) {
        if (userService.getStudentByFIO(StudentEntity.fromStudentToEntity(student)).isPresent()) {
            return ResponseEntity.badRequest().body("Ай ай ай такой студент уже есть!!!!");
        }

        return ResponseEntity.ok(userService.save(StudentEntity.fromStudentToEntity(student)).toString());
    }
}
