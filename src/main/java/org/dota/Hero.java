package org.dota;

import static java.lang.Math.max;
import static java.lang.Math.min;

public abstract class Hero {
    public final static int MIN_STAT_VALUE = 1;
    private final String name;
    private final int maxHealth;
    private final int baseAttack;
    private int health;


    public Hero(String name, int maxHealth, int baseAttack) {
        this.maxHealth = max(maxHealth, MIN_STAT_VALUE);
        this.baseAttack = max(baseAttack, MIN_STAT_VALUE);
        this.health = this.maxHealth;
        this.name = name == null ? "" : name;
    }

    abstract String getResources();
    abstract void attack(Hero target);
    abstract ActionType makeTurn(Hero target);
    void takeDamage(int damage){
        if (damage <= 0 || !isAlive()){
            return;
        }
        health -= damage;
        health = max(health, 0);
        if (health == 0){
            System.out.println(name + " пал в бою!");
        }
    }

    void heal(int amount){
        if (amount <= 0){
            return;
        }
        health += amount;
        health = min(health, maxHealth);
    }

    boolean isAlive(){
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public int getHealth() {
        return health;
    }
}
