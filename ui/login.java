package 文字战斗fright.ui;

import 文字战斗fright.baen.User;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class login {

    public void start() {

        ArrayList<User> Users = new ArrayList<>();

        while (true) {//ctrl+alt+t快捷键循环包裹代码
            System.out.println("╔════════════════════════════════╗");
            System.out.println("    🎮 欢迎来到文字格斗游戏 🎮   ");
            System.out.println("╚════════════════════════════════╝");
            System.out.println("请选择操作：1登录 2注册 3退出");

            Scanner sc = new Scanner(System.in);
            String choose = sc.next();

            switch (choose) {
                case "1" -> login(Users);
                case "2" -> register(Users);
                case "3" -> {
                    System.out.println("退出游戏");
                    System.exit(0);
                }
                default -> System.out.println("输入有误，请重新选择");
            }
        }
    }


    //        1. 键盘录入用户名
//        2. 键盘录入密码
//        3. 键盘录入验证码
//        4. 登录最多重试三次，三次错误账号锁定
//        **验证要求：**
//
//	用户名如果未注册提示：用户名未注册，请先注册
//
//	用户被锁定提示：用户xxx已经锁定，请联系黑马程序员官方客服：XXX-XXXXX
//
//	验证码错误提示：验证码输入错误，请重新输入，并生成一个新的验证码
//
//	判断用户名和密码是否正确，有3次机会，满3次账户锁定。


    public void login(ArrayList<User> Users) {
        //       System.out.println("登录功能暂未实现");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String name = sc.next();
        if (CheckRepeat(Users, name)) {
            System.out.println("用户名未注册，请先注册");
            return;
        }
        if (!GetStatus(Users, name)) {
            System.out.println("用户" + name + "已经锁定，请联系官方客服：XXX-XXXXX");
            return;
        }


        for (int i = 0; i < 3; ) {
            System.out.println("请输入密码：");
            String password = sc.next();

            while (true) {
                String code = CreatCode();
                System.out.println("验证码为：" + code);
                System.out.println("请输入验证码：");
                String inputcode = sc.next();

                if (!code.equals(inputcode)) {
                    System.out.println("验证码输入错误，请重新输入");
                    continue;
                }
                break;
            }

            if(!(CheckPassword(name, password, Users))) {
                i++;
                if (i < 3) {
                    System.out.println("密码错误，请重新输入。你还剩" + (3 - i) + "次登录机会");
                    continue;
                } else {
                    LockAccount(Users, name);
                    System.out.println("账号已锁定，请联系官方客服：XXX-XXXXX");
                    return;
                }
            }
            System.out.println("登录成功，欢迎你：" + name);
            return;
        }

        //登录成功
        System.out.println("登录成功，欢迎你：" + name);
        return;


    }


    public void register(ArrayList<User> Users) {

        Scanner sc = new Scanner(System.in);

//        * 用户名唯一
//                * 长度必须在3 ~ 16位
//                * 只能由字母、数字组成，不能是纯数字
        while (true) {
            System.out.println("请输入你的用户名：");
            String name = sc.next();

            if (!CheckLen(3, 16, name)) {
                System.out.println("用户名长度必须在3~16位，请重新输入");
                continue;
            }
            if (!CheckName(name)) {
                System.out.println("用户名只能由字母、数字组成，不能是纯数字，请重新输入");
                continue;
            }
            if (!CheckRepeat(Users, name)) {
                System.out.println("用户名已存在，请重新输入");
                continue;
            }

//            * 密码长度3 ~ 8位
//                    * 只能是字母加数字的组合，不能有其他字母

            System.out.println("请输入你的密码：");
            String password = sc.next();

            if (!CheckLen(3, 8, password)) {
                System.out.println("密码长度必须在3~8位，请重新输入");
                continue;
            }

            if (!CheckName(password)) {
                System.out.println("密码只能由字母、数字组成，不能是纯数字，请重新输入");
                continue;
            }

            System.out.println("请确认你的密码：");
            String passwordconform = sc.next();


            if (password.equals(passwordconform)) {
                User u = new User(name, password);
                Users.add(u);
                System.out.println("注册成功，欢迎你：" + name);
                break;
            } else {
                System.out.println("两次输入的密码不一致，请重新注册");
            }
        }

    }

    public int Findindex(ArrayList<User> Users, String name) {
        for (int i = 0; i < Users.size(); i++) {
            User u = Users.get(i);
            if (u.getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public boolean GetStatus(ArrayList<User> Users, String name) {
        for (int i = 0; i < Users.size(); i++) {
            User u = Users.get(i);
            if (u.getName().equals(name)) {
                return u.isStatus();
            }
        }
        return false;
    }

    //  #### 2.3 验证码规则：
//
//	长度为5
//
//	由4位大写或者小写字母和1位数字组成，同一个字母可重复
//
//	数字可以出现在任意位置
    public String CreatCode() {
        StringBuilder code = new StringBuilder();
        ArrayList<Character> c = new ArrayList<>();
        ArrayList<Integer> n = new ArrayList<>();
        for (int i = 'a'; i <= 'z'; i++) {
            c.add((char) (i));
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            c.add((char) (i));
        }
        for (int i = 0; i <= 9; i++) {
            n.add(i);
        }
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            int index = rand.nextInt(c.size());
            code.append(c.get(index));
        }
        int index = rand.nextInt(n.size());
        int location = rand.nextInt(5);//数字插入位置索引,
        code.insert(location, n.get(index));
        return code.toString();
    }

    public void LockAccount(ArrayList<User> Users, String name) {
        for (int i = 0; i < Users.size(); i++) {
            User u = Users.get(i);
            if (u.getName().equals(name)) {
                u.setStatus(false);
            }
        }

    }


    public boolean CheckPassword(String name, String password, ArrayList<User> Users) {
        for (int i = 0; i < Users.size(); i++) {
            User u = Users.get(i);
            if (u.getName().equals(name) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


    public boolean CheckLen(int minlen, int maxlen, String str) {
        return (str.length() >= minlen && str.length() <= maxlen);
    }

    public boolean CheckName(String name) {
        //只能由字母、数字组成，不能是纯数字
        int charcount = 0;
        int numcount = 0;
        int othercount = 0;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                charcount++;
            } else if ((c >= '0' && c <= '9')) {
                numcount++;
            } else {
                othercount++;
            }
        }
        if (othercount > 0 || charcount == 0) {
            return false;
        }
        return true;
    }

    public boolean CheckRepeat(ArrayList<User> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }


}