package ru.mirea.Pegov.pkmn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.Pegov.pkmn.dao.CardDao;
import ru.mirea.Pegov.pkmn.dao.StudentDao;
import ru.mirea.Pegov.pkmn.entity.CardEntity;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;
import ru.mirea.Pegov.pkmn.models.Card;
import ru.mirea.Pegov.pkmn.models.Student;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardDao cardDao;
    private final StudentDao studentDao;

    @Override
    public CardEntity getCardById(UUID id) {
        return cardDao.getCardById(id);
    }

    @Override
    public CardEntity getCardByFIO(StudentEntity student) {
        return cardDao.getCardByStudent(student);
    }

    @Override
    public CardEntity getCardByName(String name) {
        return cardDao.getCardByName(name);
    }

    @Override
    @Transactional
    public CardEntity saveCard(CardEntity card) {
        if (cardDao.cardExists(card)) {
            throw new IllegalArgumentException("есть такая карточка!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        if(card.getPokemonOwner() != null){
            if(studentDao.studentExists(card.getPokemonOwner())){
                card.setPokemonOwner(studentDao.getStudentsByFIO(card.getPokemonOwner()).getFirst());
            }
            else {
                card.setPokemonOwner(studentDao.saveStudent(card.getPokemonOwner()));
            }
        }
        if(card.getEvolvesFrom() != null)
        {
            if(cardDao.cardExists(card.getEvolvesFrom())){
                card.setEvolvesFrom(cardDao.getCardByName(card.getEvolvesFrom().getName()));
            }
            else {
                card.setEvolvesFrom(cardDao.saveCard(card.getEvolvesFrom()));
            }
        }

        return cardDao.saveCard(card);
    }


    @Override
    public List<CardEntity> findAllard(){
        return cardDao.findAllCard();
    }


}
