package 文字战斗fright.baen;

public class Character {
    public String name;
    public int HP;
    public int MaxHP;
    public int Attack;
    public int Defense;

    public Character() {
    }

    public Character(String name, int HP, int maxHP, int attack, int defense) {
        this.name = name;
        this.HP = HP;
        MaxHP = maxHP;
        Attack = attack;
        Defense = defense;
    }

    //判断是否存活
    public boolean isAlive(){
        return HP > 0;
    }

    //恢复血量
     public void heal(int amount){
        HP += amount;
        if (HP > MaxHP){
            HP = MaxHP;
        }
     }

     //受到伤害
    public void takeDamage(int amount){
        int damage = amount;
        if (damage <= 0){
            damage = 1;
        }
        HP -= damage;
        if (HP <= 0){
            HP = 0;
        }
    }

    //展示当前状态
    //zhangsan [HP: 100/100, ATK: 10, DEF: 0]
    public String show(){
        return name+ "[HP:" + HP+"/"+MaxHP+",ATK:"+Attack+",DEF:"+Defense+"]";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMaxHP() {
        return MaxHP;
    }

    public void setMaxHP(int maxHP) {
        MaxHP = maxHP;
    }

    public int getAttack() {
        return Attack;
    }

    public void setAttack(int attack) {
        Attack = attack;
    }

    public int getDefense() {
        return Defense;
    }

    public void setDefense(int defense) {
        Defense = defense;
    }
}
