package ru.mirea.Pegov.pkmn.service;

import ru.mirea.Pegov.pkmn.entity.CardEntity;
import ru.mirea.Pegov.pkmn.models.Card;
import ru.mirea.Pegov.pkmn.models.Student;

import java.util.List;
import java.util.UUID;

public interface CardService {

    CardEntity getCardById(UUID id);

    Card getCardByFIO(Student student);

    CardEntity getCardByName(String name);

    CardEntity saveCard(CardEntity card);

    List<CardEntity> findAllcard();
}
