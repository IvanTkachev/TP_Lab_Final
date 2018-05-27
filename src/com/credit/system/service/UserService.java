package com.credit.system.service;

import com.credit.system.entity.User;

public interface UserService {

    User getUser(String name, String password);
}
