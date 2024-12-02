package ru.mirea.Pegov.pkmn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.Pegov.pkmn.dao.CardDao;
import ru.mirea.Pegov.pkmn.entity.CardEntity;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;
import ru.mirea.Pegov.pkmn.models.Card;
import ru.mirea.Pegov.pkmn.models.PokemonStage;
import ru.mirea.Pegov.pkmn.models.Student;
import ru.mirea.Pegov.pkmn.repository.CardEntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardDao cardDao;

    @Override
    public Card getCardById(UUID id) {
        CardEntity cardEntity = cardDao.getCardById(id);
        return Card.fromEntityCard(cardEntity);
    }

    @Override
    public Card getCardByFIO(Student student) {
        CardEntity cardEntity = cardDao.getCardByStudent(student);

        return Card.fromEntityCard(cardEntity);
    }

    @Override
    public Card getCardByName(String name) {
        return Card.fromEntityCard(cardDao.getCardByName(name));
    }

    @Override
    @Transactional
    public CardEntity saveOrUpdateCard(CardEntity card) {
        // карта должна иметь владельца или связь с эволюционной картой
        if (card.getPokemonOwner() == null ||
                (card.getPokemonStage() == PokemonStage.BASIC && card.getEvolvesFrom() == null)) {
            throw new RuntimeException("Card must have an owner or evolutionary link");
        }

        // Проверка уникальности карты
        CardEntity cardEntity = cardDao.getCardByName(card.getName());
        if (cardEntity.getNumber().equals(card.getNumber()) &&
                !cardEntity.getId().equals(card.getId())) {
            card.setId(cardEntity.getId());
            return null;
        }

        return cardDao.saveCard(card);
    }


    @Override
    public List<CardEntity> findAllcard(){
        return cardDao.findAllCard();
    }
}
