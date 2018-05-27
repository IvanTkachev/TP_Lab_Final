package com.credit.system.service.impl;

import com.credit.system.dao.UserDao;
import com.credit.system.dao.impl.UserDaoImpl;
import com.credit.system.entity.User;
import com.credit.system.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User getUser(String name, String password) {
        return userDao.getUser(name, password);
    }
}
