package com.example.tugaslatihanpenyimpananinternal;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("select * from user")
    List<User> getAllUser();

    @Query("select * from user WHERE nama = :username LIMIT 1")
    User retrieveSingleUser(String username);

    @Insert
    void insertNewUser(User user);
}
