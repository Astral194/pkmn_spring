package ru.mirea.Pegov.pkmn.service;


import ru.mirea.Pegov.pkmn.entity.StudentEntity;
import ru.mirea.Pegov.pkmn.models.Student;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<StudentEntity> getStudentsByGroup(String group);

    StudentEntity getStudentByFIO(Student student);

    void saveStudent(Student student);

    List<StudentEntity> findAllStudents();
}
