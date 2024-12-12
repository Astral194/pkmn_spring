package ru.mirea.Pegov.pkmn.dao;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

    @SneakyThrows
    public CardEntity getCardById(UUID id) {
        return cardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Card with ID " + id + " not found")
        );
    }

    @SneakyThrows
    public CardEntity getCardByName(String name) {
        return cardRepository.findByName(name).stream().findFirst().orElseThrow(
                () -> new RuntimeException("Card with this name " + name + " not found")
        );
    }

    @SneakyThrows
    public CardEntity getCardByStudent(StudentEntity student) {
        StudentEntity students = studentRepository.findByFirstNameAndSurNameAndFamilyNameAndGroup(student.getFirstName(),
                        student.getSurName(), student.getFamilyName(), student.getGroup())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return cardRepository.findByPokemonOwner_id(students.getId()).getFirst();
    }

    public CardEntity saveCard(CardEntity card) {
        return cardRepository.saveAndFlush(card);
    }

    public List<CardEntity> findAllCard(){
        return cardRepository.findAll();
    }

    public boolean cardExists(CardEntity card) {
        return cardRepository.existsByName(card.getName());
    }

}
