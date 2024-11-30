package ru.mirea.Pegov.pkmn.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ru.mirea.Pegov.pkmn.models.AttackSkill;

import java.util.ArrayList;
import java.util.List;

public class UtilsJSON {
    public static ArrayList<AttackSkill> parseAttackSkillsFromJson(String json) {
        ArrayList<AttackSkill> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode tmp;
        try{
            tmp  = (ArrayNode) objectMapper.readTree(json);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
        return getAttackSkills(tmp, result);
    }
    public static ArrayList<AttackSkill> parseAttackSkillsFromJson(ArrayNode json) {
        ArrayList<AttackSkill> result = new ArrayList<>();
        return getAttackSkills(json, result);
    }

    private static ArrayList<AttackSkill> getAttackSkills(ArrayNode json, ArrayList<AttackSkill> result) {
        for(int i = 0; i < json.size(); i++){
            JsonNode ji = json.get(i);
            AttackSkill as = new AttackSkill();
            as.setDescription(ji.findValue("text").toString().replace("\"", ""));
            as.setCost(ji.findValue("cost").toString());
            as.setDamage((ji.get("damage").asInt()));
            as.setName(ji.findValue("name").toString());
            result.add(as);
        }
        return result;
    }

    public static String AttackSkillsToJson(List<AttackSkill> attackSkills) {
        StringBuilder query = new StringBuilder();
        query.append("[");
        for (AttackSkill as : attackSkills){
            query.append(as.toString().replace('\'', '`')).append(", ");
        }
        query.delete(query.length()-2, query.length()-1);
        query.append("]");
        return query.toString();
    }
}