package com.credit.system.dao;

import com.credit.system.entity.User;

public interface UserDao {

    User getUser(String name, String password);
}
