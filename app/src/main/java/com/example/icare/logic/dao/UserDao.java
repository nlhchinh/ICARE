package com.example.icare.logic.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.icare.logic.model.User;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    // Add a new query method for login
    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    User login(String email, String password);

    // Add a new query method for getting user by email
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    User getUserByEmail(String email);

    // Add a new query method for getting user by ID
    @Query("SELECT * FROM users WHERE userId = :userId LIMIT 1")
    User getUserById(String userId);
}
