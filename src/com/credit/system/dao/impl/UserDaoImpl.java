package com.credit.system.dao.impl;

import com.credit.system.dao.DAO;
import com.credit.system.dao.UserDao;
import com.credit.system.entity.Role;
import com.credit.system.entity.User;
import com.credit.system.service.SqlService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends DAO implements UserDao {


    @Override
    public User getUser(String name, String password) {
        Connection connection = poolInst.getConnection();
       User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql.
                    getProperty(SqlService.SQL_GET_USER));
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet userSet = statement.executeQuery();
            while (userSet.next()) {
                user = new User(userSet.getInt(1),
                        userSet.getString(2),
                        userSet.getString(3),
                        new Role(userSet.getInt(4),
                                        userSet.getString(5)));
            }

            userSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            poolInst.footConnection(connection);
        }
        return user;
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.getUser("Clerk", "000").getUsername());
    }
}
