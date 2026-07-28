package 文字战斗fright.bean;

import java.util.Random;

public class User {
    //属性：id、用户名、密码、状态
    private String id;
    private String name;
    private String password;
    private boolean status;

    public String CreatId() {
        StringBuilder sb = new StringBuilder("Meng");
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }


    public User() {
        id = CreatId();
        status = true;
    }

    public User(String name, String password) {
        this.id = CreatId();
        this.name = name;
        this.password = password;
        this.status = true;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isStatus() {
        return status;
    }
}
