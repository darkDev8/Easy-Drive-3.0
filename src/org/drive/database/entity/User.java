package org.drive.database.entity;

public class User {
    private int id;
    private int space;

    private String loginUser;
    private String loginPass;
    private String passHint;
    private String createDate;

    public User(String loginUser, String loginPass, String passHint, String createDate) {
        this.loginUser = loginUser;
        this.loginPass = loginPass;
        this.passHint = passHint;
        this.createDate = createDate;
    }

    public User() {
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getLoginPass() {
        return loginPass;
    }

    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassHint() {
        return passHint;
    }

    public void setPassHint(String passHint) {
        this.passHint = passHint;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }
}
