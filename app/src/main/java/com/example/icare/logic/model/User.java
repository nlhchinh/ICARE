package com.example.icare.logic.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    @NonNull
    private String userId = "";
    private String userName;
    private String email;
    private String password;
    private String status;
    private String createDay;
    private String lastLogin;
    private String lastLogout;


    @Ignore
    public User() {
    }

    public User(@NonNull String userId, String userName, String email, String password, String status, String createDay, String lastLogin, String lastLogout) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.createDay = createDay;
        this.lastLogin = lastLogin;
        this.lastLogout = lastLogout;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDay() {
        return createDay;
    }

    public void setCreateDay(String createDay) {
        this.createDay = createDay;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastLogout() {
        return lastLogout;
    }

    public void setLastLogout(String lastLogout) {
        this.lastLogout = lastLogout;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", createDay='" + createDay + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", lastLogout='" + lastLogout + '\'' +
                '}';
    }
}
