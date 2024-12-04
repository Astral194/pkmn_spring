package ru.mirea.Pegov.pkmn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.Pegov.pkmn.entity.CardEntity;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardEntityRepository extends JpaRepository<CardEntity, UUID> {

    Optional<CardEntity> findById(UUID  id); // Поиск по ID

    List<CardEntity> findAll(); // Найти все строки

    List<CardEntity> findAllById(Iterable<UUID > ids); // Найти все строки по ID

    List<CardEntity> findByName(String name);

    CardEntity findByPokemonOwner_id(UUID studentId);

    boolean existsByName(String name);
}
