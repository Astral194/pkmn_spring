package ru.mirea.Pegov.pkmn.service;


import ru.mirea.Pegov.pkmn.entity.StudentEntity;
import ru.mirea.Pegov.pkmn.models.Student;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<StudentEntity> getStudentsByGroup(String group);

    Optional<StudentEntity> getStudentByFIO(StudentEntity student);

    List<StudentEntity> findAllStudents();

    StudentEntity save(StudentEntity studentEntity);
}
