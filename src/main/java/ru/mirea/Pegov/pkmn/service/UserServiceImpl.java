package ru.mirea.Pegov.pkmn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.Pegov.pkmn.dao.StudentDao;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService  {

    private final StudentDao studentDao;

    @Override
    public List<StudentEntity> getStudentsByGroup(String group) {
        return studentDao.getStudentsByGroup(group);
    }

    @Override
    public Optional<StudentEntity> getStudentByFIO(StudentEntity student) {
        List<StudentEntity> students = studentDao.getStudentsByFIO(student);
        if (students.size() > 1) {
            throw new RuntimeException("More than one user found with the provided full name.");
        }
        return students.stream().findFirst();
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
