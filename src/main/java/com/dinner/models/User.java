package com.dinner.models;

/**
 * Created by brut on 2/16/16.
 */
public class User {
    private String login;
    private String hashPassword;

    public User(String login, String hashPassword) {
        this.login = login;
        this.hashPassword = hashPassword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }
}
