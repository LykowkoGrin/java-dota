package org.dota;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.dota.ActionType.*;

public class Mage extends Hero implements Castable, Restable{
    private final int maxMana;
    private int mana;
    public Mage(String name, int maxHealth, int baseAttack, int maxMana) {
        super(name, maxHealth, baseAttack);
        this.maxMana = max(maxMana, MIN_STAT_VALUE);
        mana = this.maxMana;
    }

    @Override
    public void castSpecialSkill(Hero target) {
        if (target == null){
            return;
        }
        if(mana < 25){
            System.out.println("Не хватает маны для ульты");
            return;
        }
        System.out.println(getName() + " ударил на " + getBaseAttack() * 3);
        target.takeDamage(getBaseAttack() * 3);
        mana -= 25;
    }

    @Override
    public boolean canCast() {
        return mana >= 25;
    }

    @Override
    String getResources() {
        return "Мана: " + mana;
    }

    @Override
    void attack(Hero target) {
        if (target == null){
            return;
        }
        if (mana >= 10){
            System.out.println(getName() + " ударил на " + getBaseAttack() * 2);
            target.takeDamage(getBaseAttack() * 2);
            mana -= 10;
        }
        else{
            System.out.println(getName() + " ударил на " + getBaseAttack() / 2 + ", восстановил 5");
            target.takeDamage(getBaseAttack() / 2);
            mana = min(maxMana, mana + 5);
        }
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
    public void rest() {
        mana = maxMana;
        heal(getBaseAttack());
        System.out.println(getName() + " восстановил " + getBaseAttack());
    }

    @Override
    public boolean needsRest() {
        return (mana == 0 || ((double) getHealth() / getMaxHealth()) * 100 < 30.0);
    }
}
