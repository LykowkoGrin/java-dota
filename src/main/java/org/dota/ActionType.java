package org.dota;

public enum ActionType {
    BASE_ATTACK("базовая атака"),
    SPECIAL_SKILL("ульта"),
    REST("пропуск хода");


    private String description;
    private ActionType(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
}
