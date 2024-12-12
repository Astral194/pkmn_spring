package ru.mirea.Pegov.pkmn.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import ru.mirea.Pegov.pkmn.converters.SkillDeserializer;
import ru.mirea.Pegov.pkmn.models.*;
import jakarta.persistence.*;

import ru.mirea.Pegov.pkmn.converters.EnergyTypeConverter;

import static org.hibernate.type.SqlTypes.JSON;

@Entity
@Table(name = "cards")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name="name")
    private String name;
    @Column(columnDefinition = "smallint")
    private int hp;

    @Column(name="card_number")
    private String number;
    @Enumerated(EnumType.STRING)
    @Column(name="stage")
    private PokemonStage pokemonStage;
    @Column(name="retreat_cost")
    private String retreatCost;

    @Convert(converter = EnergyTypeConverter.class)
    @Column(name="pokemon_type", nullable = true)
    private EnergyType pokemonType;
    @Convert(converter = EnergyTypeConverter.class)
    @Column(name="weakness_type", nullable = true)
    private EnergyType weaknessType;
    @Convert(converter = EnergyTypeConverter.class)
    @Column(name="resistance_type", nullable = true)
    private EnergyType resistanceType;

    @Column(name="game_set")
    private String gameSet;
    @Column(name="regulation_mark")
    private char regulationMark;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "pokemon_owner_id")
    private StudentEntity pokemonOwner;

    @JdbcTypeCode(JSON)
    @Column(name="attack_skills", columnDefinition = "JSON")
    @JsonDeserialize(using = SkillDeserializer.class)
    private List<AttackSkill> skills;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "evolves_from_id")
    private CardEntity evolvesFrom;

    public static CardEntity fromCardToEntity(Card card) {
        CardEntityBuilder builder = CardEntity.builder()
                .name(card.getName())
                .number(card.getNumber())
                .retreatCost(card.getRetreatCost())
                .gameSet(card.getGameSet())
                .pokemonStage(card.getPokemonStage())
                .pokemonType(card.getPokemonType())
                .weaknessType(card.getWeaknessType())
                .resistanceType(card.getResistanceType())
                .regulationMark(card.getRegulationMark())
                .hp(card.getHp())
                .pokemonOwner(StudentEntity.fromStudentToEntity(card.getPokemonOwner()))
                .skills(card.getSkills());
        if (card.getEvolvesFrom() != null)
        {
            builder.evolvesFrom(fromCardToEntity(card.getEvolvesFrom()));
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "Card{" +
                "pokemonStage=" + pokemonStage +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", evolvesFrom=" + evolvesFrom +
                ", skills=" + skills +
                ", pokemonType=" + pokemonType +
                ", weaknessType=" + weaknessType +
                ", resistanceType=" + resistanceType +
                ", retreatCost='" + retreatCost + '\'' +
                ", gameSet='" + gameSet + '\'' +
                ", regulationMark=" + regulationMark +
                ", owner=" + ((pokemonOwner != null) ? pokemonOwner.toString() : pokemonOwner)+
                '}';
    }
}