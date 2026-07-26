package 文字战斗fright.ui;

import 文字战斗fright.baen.EnemyCharachter;
import 文字战斗fright.baen.HeroCharacter;

import java.util.ArrayList;
import java.util.Scanner;

public class FightingGame {
    public void gameStart(String userName) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println(" \uD83C\uDFAE      " + userName + ":欢迎来到文字格斗游戏      \uD83C\uDFAE");
        System.out.println("╚════════════════════════════════════════╝");

        HeroCharacter HC= creatPlayerCharacter(userName);
        System.out.println("角色创建成功！");
        System.out.println("\uD83C\uDF1F 初始属性："+userName+" [HP: "+ HC.HP+"/"+ HC.MaxHP+", ATK:"+ HC.Attack+", DEF: "+ HC.Defense+"]");
        System.out.println("\uD83C\uDF1F 拥有技能："+showSkill(HC.SkillList));

        //创建敌人信息
//        | 敌人名称 | 生命值 | 攻击力 | 防御力 | 技能（变量）                                           |
//| -------- | ------ | ------ | ------ | ------------------------------------------------------ |
//| 初级战士 | 80     | 15     | 10     | 猛击（150%伤害）                                       |
//| 敏捷刺客 | 60     | 20     | 5      | 快速攻击（2次50%伤害）                                 |
//| 重装坦克 | 120    | 10     | 20     | 防御姿态（下回合伤害减半） buff（ boolean defendding） |
//| 神秘法师 | 70     | 25     | 8      | 火球术（180%伤害）                                     |
        ArrayList<EnemyCharachter> EnemyCharacter = new ArrayList<>();
        EnemyCharacter.add(new EnemyCharachter("初级战士", 80, 80, 15, 10, "猛击"));
        EnemyCharacter.add(new EnemyCharachter("敏捷刺客", 60, 60, 20, 5, "快速攻击"));
        EnemyCharacter.add(new EnemyCharachter("重装坦克", 120, 120, 10, 20, "防御姿态"));
        EnemyCharacter.add(new EnemyCharachter("神秘法师", 70, 70, 25, 8, "火球术"));

    }

    public HeroCharacter creatPlayerCharacter(String userName) {
        System.out.println("创建你的角色：");
        System.out.println("您的角色名称为：" + userName);
        Scanner sc = new Scanner(System.in);
        //属性分配
        while (true) {
            int point = 20;
            System.out.println("请分配属性点 (共20点):\n" +
                    "1. 生命值 (每点+10 HP)\n" +
                    "2. 攻击力 (每点+2 ATK)\n" +
                    "3. 防御力 (每点+1 DEF)");

            System.out.println("分配点数到 生命值 (剩余点数: "+point+"):");
            int HPpoint = sc.nextInt();
            if (point - HPpoint<0||HPpoint <0){
                System.out.println("分配点数错误，请重新分配");
                continue;
            }else {
                point -= HPpoint;
            }


            System.out.println("分配点数到 攻击力 (剩余点数: "+point+"):");
            int ATpoint = sc.nextInt();
            if (point - ATpoint<0||ATpoint <0){
                System.out.println("分配点数错误，请重新分配");
                continue;
            }else {
                point -= ATpoint;
            }


            System.out.println("分配点数到 防御力 (剩余点数: "+point+"):");
            int DFpoint = sc.nextInt();
            if (point - DFpoint<0||DFpoint <0){
                System.out.println("分配点数错误，请重新分配");
                continue;
            }else {
                point -= DFpoint;
            }

            //完成分配
            HeroCharacter newHero = new HeroCharacter();
            newHero.setMaxHP(100+10*HPpoint);
            newHero.setHP(newHero.getMaxHP());
            newHero.setAttack(10+2*ATpoint);
            newHero.setDefense(DFpoint);
            //添加技能
            newHero.SkillList.add("普通攻击");
            newHero.SkillList.add("强力一击");
            newHero.SkillList.add("生命汲取");
            return newHero;
        }

    }

    public String showSkill(ArrayList<String> skillList){
        for (int i = 0; i < skillList.size(); i++) {
            StringBuilder SB = new StringBuilder();
            SB.append(skillList.get(i));
            if (i!=skillList.size()-1){
                SB.append(", ");
            }
        return SB.toString();
        }

    }

}