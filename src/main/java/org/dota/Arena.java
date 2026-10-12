package org.dota;

public class Arena {
    private final Hero fighter1;
    private final Hero fighter2;
    private int roundCounter;
    Arena(Hero fighter1, Hero fighter2){
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
    }

    void startTournament(){
        if (fighter1 == null || fighter2 == null){
            System.out.println("Дожны быть два героя");
            return;
        }
        while (fighter1.isAlive() && fighter2.isAlive()){
            roundCounter++;
            System.out.println("Раунд: " + roundCounter);
            Hero attacker, defender;
            if(Math.random() < 0.5) {
                attacker = fighter1;
                defender = fighter2;
            } else{
                attacker = fighter2;
                defender = fighter1;
            }
            ActionType action = attacker.makeTurn(defender);
            System.out.println("Игрок " + attacker.getName() + " совершил действие: " + action.getDescription());

            if(!defender.isAlive()){
                break;
            }
            Hero newAttacher = defender;
            defender = attacker;
            attacker = newAttacher;

            action = attacker.makeTurn(defender);
            System.out.println("Игрок " + attacker.getName() + " совершил действие: " + action.getDescription());

            System.out.println("Здоровье: ");
            System.out.println(attacker.getName() + ": " + attacker.getHealth());
            System.out.println(defender.getName() + ": " + defender.getHealth());
            System.out.println("Ресурсы: ");
            System.out.println(attacker.getName() + ": " + attacker.getResources());
            System.out.println(defender.getName() + ": " + defender.getResources());
        }
        Hero winner = fighter1.isAlive() ? fighter1 : fighter2;
        System.out.println("Выиграл: " + winner.getName());
    }
}
