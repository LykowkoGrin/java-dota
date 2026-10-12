package org.dota;

import static java.lang.Math.max;
import static org.dota.ActionType.*;

public class Warrior extends Hero implements Castable, Restable{
    private final int armor;
    public Warrior(String name, int maxHealth, int baseAttack, int armor) {
        super(name, maxHealth, baseAttack);
        this.armor = max(armor, MIN_STAT_VALUE);
    }

    @Override
    String getResources() {
        return "Броня: " + armor;
    }

    @Override
    void attack(Hero target) {
        if (target == null){
            return;
        }
        System.out.println(getName() + " ударил на " + getBaseAttack());
        target.takeDamage(getBaseAttack());
    }

    @Override
    ActionType makeTurn(Hero target) {
        if(canCast()){
            castSpecialSkill(target);
            return SPECIAL_SKILL;
        }
        else if(needsRest()){
            rest();
            return REST;
        }
        else{
            attack(target);
            return BASE_ATTACK;
        }
    }

    @Override
    void takeDamage(int damage){
        int finalDamage = max(damage - armor,1);
        super.takeDamage(finalDamage);
    }

    @Override
    public void castSpecialSkill(Hero target) {
        if (target == null){
            return;
        }
        if(!canCast()){
            System.out.println("Воин попытался ударить щитом, но потерял равновесие!");
            return;
        }
        int damage = getBaseAttack() + (armor * 2);
        System.out.println(getName() + " ударил на " + damage);
        target.takeDamage(damage);
    }

    @Override
    public boolean canCast() {
        return ((double) getHealth() / getMaxHealth()) * 100 < 50.0;
    }

    @Override
    public void rest() {
        heal(armor * 2);
        System.out.println(getName() + " восстановил " + armor * 2);
    }

    @Override
    public boolean needsRest() {
        return ((double) getHealth() / getMaxHealth()) * 100 < 15.0;
    }
}
