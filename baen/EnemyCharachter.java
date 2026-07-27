package 文字战斗fright.baen;

import java.util.ArrayList;

public class EnemyCharachter extends Character {
    public String Skill;
    public boolean defending;

    public EnemyCharachter() {
    }

    public EnemyCharachter(String name, int HP, int maxHP, int attack, int defense, String skill) {
        super(name, HP, maxHP, attack, defense);
        Skill = skill;
    }

    @Override
    public void takeDamage(int amount) {
        if (defending) {
            System.out.println("对手开启了防御姿态，伤害减半");
            amount = amount/2;
            defending = false;
        }
        super.takeDamage(amount);
    }
}
