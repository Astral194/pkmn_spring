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

    @GetMapping("")
    public List<CardEntity> getAllCards() {
        return cardService.findAllard();
    }


    @GetMapping("/{name}")
    public CardEntity getCardByName(@PathVariable String name) {
        return cardService.getCardByName(name);
    }


    @GetMapping("/owner")
    public CardEntity getCardByFIO(@RequestBody StudentEntity student) {
        return cardService.getCardByFIO(student);
    }


    @GetMapping("/id/{id}")
    public CardEntity getCardById(@PathVariable UUID id) {
        return cardService.getCardById(id);
    }


    @PostMapping
    public ResponseEntity<String> createCars(@RequestBody Card card) {
        if (card.getPokemonOwner() == null){
            return ResponseEntity.badRequest().body("Бро у карты должен быть владелец.");
        }
        CardEntity entity = CardEntity.fromCardToEntity(card);
        return ResponseEntity.ok(cardService.saveCard(entity).toString());
    }


    @GetMapping("/card-image")
    public ResponseEntity<Void> getCardImage(@RequestBody Card card) {
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
