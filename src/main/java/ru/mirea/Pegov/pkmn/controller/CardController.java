package ru.mirea.Pegov.pkmn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.Pegov.pkmn.entity.CardEntity;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;
import ru.mirea.Pegov.pkmn.models.Card;
import ru.mirea.Pegov.pkmn.models.Student;
import ru.mirea.Pegov.pkmn.service.CardService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    // i. Получить список всех карт
    @GetMapping("")
    public List<CardEntity> getAllCards() {
        return cardService.findAllard();
    }

    // ii. Получить карту по имени
    @GetMapping("/{name}")
    public CardEntity getCardByName(@PathVariable String name) {
        return cardService.getCardByName(name);
    }
    // iii. Получить карту по ФИО и группе
    @GetMapping("/owner")
    public CardEntity getCardByFIO(@RequestBody StudentEntity student) {
        return cardService.getCardByFIO(student);
    }

    // iv. Получить карту по ID
    @GetMapping("/id/{id}")
    public CardEntity getCardById(@PathVariable UUID id) {
        return cardService.getCardById(id);
    }

    // v создание карты
    @PostMapping
    public ResponseEntity<String> createCars(@RequestBody CardEntity card) {
        if (card.getPokemonOwner() == null){
            return ResponseEntity.badRequest().body("Бро у карты должен быть владелец.");
        }
        return ResponseEntity.ok(cardService.saveCard(card).toString());
    }
}
