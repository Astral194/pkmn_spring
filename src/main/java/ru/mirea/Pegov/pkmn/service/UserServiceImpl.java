package ru.mirea.Pegov.pkmn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.Pegov.pkmn.dao.StudentDao;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;
import ru.mirea.Pegov.pkmn.models.Student;
import ru.mirea.Pegov.pkmn.repository.StudentEntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService  {

    private final StudentDao studentDao;

    @Override
    public List<StudentEntity> getStudentsByGroup(String group) {
        return studentDao.getStudentsByGroup(group);
    }

    public Optional<StudentEntity> getStudentByFIO(StudentEntity student) {
        Optional<StudentEntity> students = Optional.ofNullable(studentDao.getStudentsByFIO(student));
        if (students.isPresent()) {
            throw new IllegalArgumentException("More than one user found with the provided full name.");
        }
        return students.isEmpty() ? null : students.stream().findFirst();
    }

    @Override
    public List<StudentEntity> findAllStudents() {
        return studentDao.findAllStudent();
    }

    @Override
    public StudentEntity save(StudentEntity student) {
        return studentDao.saveStudent(student);
    }

}
