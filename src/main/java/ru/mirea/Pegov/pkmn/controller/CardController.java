package ru.mirea.Pegov.pkmn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mirea.Pegov.pkmn.entity.CardEntity;
import ru.mirea.Pegov.pkmn.models.Card;
import ru.mirea.Pegov.pkmn.models.Student;
import ru.mirea.Pegov.pkmn.service.CardService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    // i. Получить список всех карт
    @GetMapping("")
    public List<CardEntity> getAllCards() {
        return cardService.findAllcard();
    }

    // ii. Получить карту по имени
    @GetMapping("/{name}")
    public Card getCardByName(@PathVariable String name) {
        return cardService.getCardByName(name);
    }
    // iii. Получить карту по ФИО и группе
    @GetMapping("/owner")
    public Card getCardByFIO(@RequestBody Student student) {
        return cardService.getCardByFIO(student);
    }

}
