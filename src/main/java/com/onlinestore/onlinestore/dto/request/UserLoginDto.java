package com.onlinestore.onlinestore.dto.request;

public class UserLoginDto {
    String login;
    String password;

    public UserLoginDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
}
