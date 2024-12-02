package ru.mirea.Pegov.pkmn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.Pegov.pkmn.dao.StudentDao;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;
import ru.mirea.Pegov.pkmn.models.Student;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService  {

    private final StudentDao studentDao;

    @Override
    public List<StudentEntity> getStudentsByGroup(String group) {
        return studentDao.getStudentsByGroup(group);
    }

    public StudentEntity getStudentByFIO(Student student) {
        return studentDao.getStudentsByFIO(student);
    }

    @Override
    public void saveStudent(Student student) {

    }

    @Override
    public List<StudentEntity> findAllStudents() {
        return studentDao.findAllStudent();
    }
}
