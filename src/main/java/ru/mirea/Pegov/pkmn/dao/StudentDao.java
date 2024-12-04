package ru.mirea.Pegov.pkmn.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;
import ru.mirea.Pegov.pkmn.repository.StudentEntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StudentDao {

    private final StudentEntityRepository studentRepository;

    // Найти студента по ID или выбросить исключение
    public List<StudentEntity> getStudentsByGroup(String group) {
        return studentRepository.getStudentByGroup(group);
    }

    // Найти студента по имени
    public Optional<StudentEntity> getStudentsByFIOandGroup(StudentEntity student) {
        return studentRepository.findByFirstNameAndSurNameAndFamilyNameAndGroup(
                student.getFirstName(),
                student.getSurName(),
                student.getFamilyName(),
                student.getGroup());
    }

    public boolean studentExists(StudentEntity student) {
        return studentRepository.existsByFirstNameAndSurNameAndFamilyName(student.getFirstName(),
                student.getSurName(),
                student.getFamilyName());
    }

    public StudentEntity getStudentsByFIO(StudentEntity student) {
        return studentRepository.findByFirstNameAndSurNameAndFamilyName(student.getFirstName(), student.getSurName(), student.getFamilyName());
    }

    // Сохранить студента
    public StudentEntity saveStudent(StudentEntity student) {
        return studentRepository.saveAndFlush(student);
    }

    public List<StudentEntity> findAllStudent(){
        return studentRepository.findAll();
    }


}
