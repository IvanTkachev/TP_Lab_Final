package com.credit.system.dao.impl;

import com.credit.system.dao.DAO;
import com.credit.system.dao.RequestDao;
import com.credit.system.entity.Request;
import com.credit.system.entity.Role;
import com.credit.system.entity.User;
import com.credit.system.entity.UserType;
import com.credit.system.service.SqlService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestDaoImpl extends DAO implements RequestDao{
    @Override
    public int create(Request request) {
        Connection connection = poolInst.getConnection();
        int id = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sql.
                    getProperty(SqlService.SQL_INSERT_REQUEST));
            statement.setString(1, request.getName());
            if (request.getType() == UserType.LEGAL){
                statement.setInt(2, 1);
            } else {
                statement.setInt(2, 1);
            }
            statement.setInt(3, request.getAmount());
            statement.executeUpdate();
            statement.close();
            statement = connection.prepareStatement(sql.getProperty((SqlService.SQL_GET_REQUEST_ID)));
            statement.setString(1, request.getName());
            statement.setInt(2, request.getAmount());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            id = resultSet.getInt(1);
            statement.close();
            statement = connection.prepareStatement(sql.getProperty(SqlService.SQL_INSERT_ATTACHMENT));
            statement.setInt(1, id);
            for (String str : request.getAttachments()){
                statement.setString(2, str);
                statement.executeUpdate();
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            poolInst.footConnection(connection);
        }
        return id;
    }

    @Override
    public void updateStatus(Request request) {
        Connection connection = poolInst.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql.
                    getProperty(SqlService.SQL_UPDATE_REQUEST_STATUS));
            statement.setInt(1, request.getRequestType().getId());
            statement.setInt(2, request.getId());
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            poolInst.footConnection(connection);
        }
    }
}
