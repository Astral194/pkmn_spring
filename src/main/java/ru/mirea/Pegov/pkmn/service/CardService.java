package ru.mirea.Pegov.pkmn.service;

import ru.mirea.Pegov.pkmn.entity.CardEntity;
import ru.mirea.Pegov.pkmn.models.Card;
import ru.mirea.Pegov.pkmn.models.Student;

import java.util.List;
import java.util.UUID;

public interface CardService {

    Card getCardById(UUID id);

    Card getCardByFIO(Student student);

    Card getCardByName(String name);

    CardEntity saveOrUpdateCard(CardEntity card);

    List<CardEntity> findAllcard();
}
