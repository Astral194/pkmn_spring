package ru.mirea.Pegov.pkmn.utils;


import ru.mirea.Pegov.pkmn.entity.CardEntity;
import ru.mirea.Pegov.pkmn.entity.StudentEntity;

public class ClassUtils {
    static public void copyStudentProperties(StudentEntity source, StudentEntity target) {
        target.setFirstName(source.getFirstName());
        target.setFamilyName(source.getFamilyName());
        target.setSurName(source.getSurName());
    }

    static public void copyCardProperties(CardEntity source, CardEntity target) {
        target.setName(source.getName());
        target.setHp(source.getHp());
        target.setPokemonStage(source.getPokemonStage());
        target.setPokemonType(source.getPokemonType());
        target.setWeaknessType(source.getWeaknessType());
        target.setResistanceType(source.getResistanceType());
        target.setSkills(source.getSkills());
        target.setRetreatCost(source.getRetreatCost());
        target.setGameSet(source.getGameSet());
        target.setRegulationMark(source.getRegulationMark());
        target.setPokemonOwner(source.getPokemonOwner());
        target.setEvolvesFrom(source.getEvolvesFrom());
    }
}