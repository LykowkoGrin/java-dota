package org.dota;


import java.util.Scanner;

public class Main {
    static Hero askUser(Scanner scanner){
        int heroType = 0;
        while (heroType != 1 && heroType != 2){
            System.out.print("Введите тип персонажа 1 — Воин, 2 — Маг: ");
            if (scanner.hasNextInt()){
                heroType = scanner.nextInt();
            }
            else{
                scanner.next();
            }
        }
        scanner.nextLine();
        System.out.print("Введите имя персонажа: ");
        String heroName = scanner.nextLine();
        System.out.print("Введите макс. здоровья: ");
        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.print("Введите целое число: ");
        }
        int maxHealth = scanner.nextInt();
        System.out.print("Введите урон с руки: ");
        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.print("Введите целое число: ");
        }
        int baseAttack = scanner.nextInt();
        Hero hero;
        if (heroType == 1){
            System.out.print("Введите макс. значение брони: ");
            while (!scanner.hasNextInt()){
                scanner.next();
                System.out.print("Введите целое число: ");
            }
            int armor = scanner.nextInt();
            hero = new Warrior(heroName, maxHealth, baseAttack, armor);
        }
        else{
            System.out.print("Введите макс. значение маны: ");
            while (!scanner.hasNextInt()){
                scanner.next();
                System.out.print("Введите целое число: ");
            }
            int mana = scanner.nextInt();
            hero = new Mage(heroName, maxHealth, baseAttack, mana);
        }
        return hero;
    }
    static void main() {
        Scanner scanner = new Scanner(System.in);
        Hero hero1 = askUser(scanner);
        Hero hero2 = askUser(scanner);
        Arena arena = new Arena(hero1, hero2);
        arena.startTournament();
    }
}
