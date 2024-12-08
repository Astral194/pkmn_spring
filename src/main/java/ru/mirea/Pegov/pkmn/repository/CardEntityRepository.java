package ru.mirea.Pegov.pkmn.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;
import ru.mirea.Pegov.pkmn.entity.CardEntity;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;
import ru.mirea.Pegov.pkmn.models.Card;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardEntityRepository extends JpaRepository<CardEntity, UUID> {
    <S extends CardEntity> S save(S entity);

    Optional<CardEntity> findById(UUID  id); // Поиск по ID

    List<CardEntity> findAll(); // Найти все строки

    List<CardEntity> findByName(String name);

    List<CardEntity> findByPokemonOwner_id(UUID studentId);

    boolean existsByName(String name);
}
