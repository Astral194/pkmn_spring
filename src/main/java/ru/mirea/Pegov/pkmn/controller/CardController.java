package ru.mirea.Pegov.pkmn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.Pegov.pkmn.entity.CardEntity;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;
import ru.mirea.Pegov.pkmn.models.Card;
import ru.mirea.Pegov.pkmn.service.CardService;
import ru.mirea.Pegov.pkmn.Rest.PokemonTcgService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    private final PokemonTcgService pokemonTcgService;

    // i. Получить список всех карт
    @GetMapping("/all")
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
    public ResponseEntity<String> createCars(@RequestBody Card card) {
        if (card.getPokemonOwner() == null){
            return ResponseEntity.badRequest().body("Бро у карты должен быть владелец.");
        }
        CardEntity entity = CardEntity.fromCardToEntity(card);
        return ResponseEntity.ok(cardService.saveCard(entity).toString());
    }

    @GetMapping("/card-image")
    public ResponseEntity<Void> getCardImage(@RequestBody CardEntity card) {
        try {
            String imageUrl = pokemonTcgService.getCardImageUrl(card.getName(), card.getNumber());
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(imageUrl));
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
