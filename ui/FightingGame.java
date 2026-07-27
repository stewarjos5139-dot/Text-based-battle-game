package 文字战斗fright.ui;

import 文字战斗fright.baen.Character;
import 文字战斗fright.baen.EnemyCharachter;
import 文字战斗fright.baen.HeroCharacter;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FightingGame {
    public void gameStart(String userName) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println(" \uD83C\uDFAE      " + userName + ":欢迎来到文字格斗游戏      \uD83C\uDFAE");
        System.out.println("╚════════════════════════════════════════╝");

        HeroCharacter HC = creatPlayerCharacter(userName);
        System.out.println("角色创建成功！");
        System.out.println("\uD83C\uDF1F 初始属性：" + userName + " [HP: " + HC.HP + "/" + HC.MaxHP + ", ATK:" + HC.Attack + ", DEF: " + HC.Defense + "]");
        System.out.println("\uD83C\uDF1F 拥有技能：" + showSkill(HC.SkillList));

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
        System.out.println("\n");
        System.out.println("═══════════════════════════════════════");
        int count = 1;
        int win = 0;
        while (HC.isAlive()){
//            怪物成长系统
//            第一场战斗：怪物是基础属性
//            玩家每连胜一场：怪物属性增加HP+10, ATK+3, DEF+2
            if (count!=1){
                for (int i = 0; i < EnemyCharacter.size(); i++) {
                    EnemyCharachter EC = EnemyCharacter.get(i);
                    EC.MaxHP += 10;
                    EC.HP = EC.MaxHP;
                    EC.Attack += 3;
                    EC.Defense += 2;
                    EC.defending = false;
                }
            }
            //随机选择敌人
            Random R = new Random();
            int index = R.nextInt(EnemyCharacter.size());
            EnemyCharachter EC = EnemyCharacter.get(index);
            System.out.println("⚔\uFE0F 第 "+count+ "场战斗开始！对手: "+EC.name);
            System.out.println("---------------------------------------");

            //回合战斗
            int Round = 1;
            while (HC.isAlive() && EC.isAlive()) {
                System.out.println("⚔\uFE0F 第 "+Round+" 回合开始！ ");
                //展示血条
//                zhangsan: [████████████████████] 100/100 HP
//                初级战士: [████████████████████] 80/80 HP
                showHP(HC);
                showHP(EC);
                //我打敌人一下

                System.out.println("===== 你的回合 =====");







                //判断敌人血量是否为0，如果为0回合结束，返回外循环，不为零继续
                //敌人打我一下
                //判断我的血量是否为0，为0结束游戏，不为0则循环，进行下一回合



            }
        }



    }

    public void yourTurn(HeroCharacter player, EnemyCharachter enemy) {
        System.out.println("===== 你的回合 =====");
        System.out.println("1. 普通攻击");
        System.out.println("2. 强力一击 (消耗10HP)");
        System.out.println("3. 生命汲取 (消耗10HP，恢复生命)");
        System.out.print("选择行动 (1-3):");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        switch (i) {
            default:{
                System.out.println("没有这个选项，默认使用普通攻击");
            }
            case 1:{
                //你对 敏捷刺客 使用了强力一击，造成 31 点伤害！
                int damage=calculateDamage(player.Attack,enemy.Defense);
                System.out.println("你对"+enemy.name+" 使用了普通攻击，造成"+damage +" 点伤害");
                enemy.takeDamage(damage);
            }
            case 2:{
               // 💥 消耗10HP，你对 敏捷刺客 使用了强力一击，造成 31 点伤害！
                if (player.HP >10) {
                    player.takeDamage(10);
                    int attack = (int) Math.round(player.Attack*1.8);
                    int damage=calculateDamage(attack,enemy.Defense);
                    System.out.println("你对"+enemy.name+" 使用了强力一击，造成"+damage +" 点伤害");
                    enemy.takeDamage(damage);
                }else {
                    System.out.println("体力不足，攻击失败");
                }

            }
            case 3:{

            }

        }


    }



    public int calculateDamage(int attack,int defense) {
        int damage = attack-defense;
        if (damage <= 0){
            damage = 1;
        }
        return damage;
    }

    public void showHP(Character c){
        StringBuilder SB = new StringBuilder();
        SB.append(c.name+": [");
        double count = ((double)c.HP/c.MaxHP)*20;
        for (int i = 1; i <= 20; i++) {
            if (i<=(int)Math.round(count)){
                SB.append("█");
            }else {
                SB.append(" ");
            }
        }
        SB.append("]");
        System.out.println(SB.toString()+" "+c.HP+"/"+c.MaxHP+" HP");
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

            System.out.println("分配点数到 生命值 (剩余点数: " + point + "):");
            int HPpoint = sc.nextInt();
            if (point - HPpoint < 0 || HPpoint < 0) {
                System.out.println("分配点数错误，请重新分配");
                continue;
            } else {
                point -= HPpoint;
            }


            System.out.println("分配点数到 攻击力 (剩余点数: " + point + "):");
            int ATpoint = sc.nextInt();
            if (point - ATpoint < 0 || ATpoint < 0) {
                System.out.println("分配点数错误，请重新分配");
                continue;
            } else {
                point -= ATpoint;
            }


            System.out.println("分配点数到 防御力 (剩余点数: " + point + "):");
            int DFpoint = sc.nextInt();
            if (point - DFpoint < 0 || DFpoint < 0) {
                System.out.println("分配点数错误，请重新分配");
                continue;
            } else {
                point -= DFpoint;
            }

            //完成分配
            HeroCharacter newHero = new HeroCharacter();
            newHero.setMaxHP(100 + 10 * HPpoint);
            newHero.setHP(newHero.getMaxHP());
            newHero.setAttack(10 + 2 * ATpoint);
            newHero.setDefense(DFpoint);
            //添加技能
            newHero.SkillList.add("普通攻击");
            newHero.SkillList.add("强力一击");
            newHero.SkillList.add("生命汲取");
            return newHero;
        }

    }

    public String showSkill(ArrayList<String> skillList) {
        StringBuilder SB = new StringBuilder();
        for (int i = 0; i < skillList.size(); i++) {
            SB.append(skillList.get(i));
            if (i != skillList.size() - 1) {
                SB.append(", ");
            }
        }
        return SB.toString();
    }

}