package ru.mirea.Pegov.pkmn.converters;

import ru.mirea.Pegov.pkmn.models.AttackSkill;

import java.util.List;

public interface AttackSkillConverter {
    String convertToDatabaseColumn(List<AttackSkill> attackSkills);

    List<AttackSkill> convertToEntityAttribute(String dbData);
}
