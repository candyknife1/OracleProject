package com.lzw.service;

import com.lzw.pojo.User;

public interface UserService {
    User login(String var1, String var2);

    boolean register(User var1);

    boolean selectByName(String var1);

    void updatePassword(String var1, String var2);

    void updateName(String var1, String var2);
}
