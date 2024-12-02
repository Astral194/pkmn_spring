package ru.mirea.Pegov.pkmn.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mirea.Pegov.pkmn.entity.CardEntity;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;
import ru.mirea.Pegov.pkmn.models.Student;
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
    public StudentEntity getStudentsByFIO(Student student) {
        return studentRepository.findByFirstNameAndSurNameAndFamilyNameAndGroup(student.getFirstName(), student.getSurName(), student.getFamilyName(), student.getGroup()).orElseThrow(
                () -> new RuntimeException("No students found\n" + student.toString())
        );
    }

    // Сохранить студента
    public StudentEntity saveStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    // Удалить студента по ID
    public void deleteStudent(UUID id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student with id " + id + " not found");
        }
        studentRepository.deleteById(id);
    }

    public List<StudentEntity> findAllStudent(){
        return studentRepository.findAll();
    }
}
