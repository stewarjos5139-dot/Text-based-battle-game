package 文字战斗fright.bean;

import java.util.ArrayList;

public class HeroCharacter extends Character {
    public ArrayList<String> SkillList;

    public HeroCharacter() {
        super();
        SkillList = new ArrayList<>();
    }

    public HeroCharacter(String name, int HP, int maxHP, int attack, int defense) {
        super(name, HP, maxHP, attack, defense);
        SkillList = new ArrayList<>();
    }
}
