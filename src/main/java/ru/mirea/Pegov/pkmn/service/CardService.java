package ru.mirea.Pegov.pkmn.service;

import ru.mirea.Pegov.pkmn.entity.CardEntity;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;
import ru.mirea.Pegov.pkmn.models.Card;
import ru.mirea.Pegov.pkmn.models.Student;

import java.util.List;
import java.util.UUID;

public interface CardService {

    CardEntity getCardById(UUID id);

    CardEntity getCardByFIO(StudentEntity student);

    CardEntity getCardByName(String name);

    CardEntity saveCard(CardEntity card);

    List<CardEntity> findAllard();
}
