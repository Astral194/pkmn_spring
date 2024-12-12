package ru.mirea.Pegov.pkmn.service;


import org.springframework.stereotype.Service;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<StudentEntity> getStudentsByGroup(String group);

    Optional<StudentEntity> getStudentByFIO(StudentEntity student);

    List<StudentEntity> findAllStudents();

    StudentEntity save(StudentEntity studentEntity);
}
