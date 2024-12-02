package ru.mirea.Pegov.pkmn.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mirea.Pegov.pkmn.entity.CardEntity;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;
import ru.mirea.Pegov.pkmn.models.Student;
import ru.mirea.Pegov.pkmn.repository.CardEntityRepository;
import ru.mirea.Pegov.pkmn.repository.StudentEntityRepository;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CardDao {

    private final CardEntityRepository cardRepository;
    private final StudentEntityRepository studentRepository;


    // Найти карту по UUID
    public CardEntity getCardById(UUID id) {
        return cardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Card with ID " + id + " not found")
        );
    }

    // Найти карту по имени
    public CardEntity getCardByName(String name) {
        return cardRepository.findByName(name).orElseThrow(
                () -> new RuntimeException("Card with this name " + name + " not found")
        );
    }

    // Найти карту по студенту
    public CardEntity getCardByStudent(Student student) {
        StudentEntity students = studentRepository.findByFirstNameAndSurNameAndFamilyNameAndGroup(student.getFirstName(),
                        student.getSurName(), student.getFamilyName(), student.getGroup())
                .orElseThrow(() -> new RuntimeException("Student not found 24242342424242424"));
        return cardRepository.findByPokemonOwner_id(students.getId());
    }

    // Сохранить карту
    public CardEntity saveCard(CardEntity card) {
        return cardRepository.save(card);
    }

    public List<CardEntity> findAllCard(){
        return cardRepository.findAll();
    }
}
