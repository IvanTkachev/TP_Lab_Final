package com.credit.system.dao.impl;

import com.credit.system.dao.DAO;
import com.credit.system.dao.RequestDao;
import com.credit.system.entity.Request;
import com.credit.system.entity.Role;
import com.credit.system.entity.User;
import com.credit.system.entity.UserType;
import com.credit.system.service.SqlService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                statement.setInt(2, 0);
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
            if (request.getAttachments() != null) {
                for (String str : request.getAttachments()) {
                    statement.setString(2, str);
                    statement.executeUpdate();
                }
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
    public void update(Request request) {
        Connection connection = poolInst.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql.
                    getProperty(SqlService.SQL_UPDATE_REQUEST));
            statement.setString(1, request.getName());
            if (request.getType() == UserType.PHYSICAL){
                statement.setInt(2, 0);
            }
            else {
                statement.setInt(2, 1);
            }
            statement.setInt(3, request.getAmount());
            statement.setInt(4, request.getRequestType().getId());
            statement.setInt(5, request.getId());
            statement.executeUpdate();
            statement.close();
            statement = connection.prepareStatement(sql.getProperty(SqlService.SQL_GET_ATTACHMENT_BY_PATH));
            statement.setInt(1, request.getId());
            if (request.getAttachments() != null){
                for (String path : request.getAttachments()){
                    statement.setString(2, path);
                    ResultSet resultSet = statement.executeQuery();
                    if (!resultSet.next()){
                        PreparedStatement preparedStatement =
                                connection.prepareStatement(sql.getProperty(SqlService.SQL_INSERT_ATTACHMENT));
                        preparedStatement.setInt(1, request.getId());
                        preparedStatement.setString(2, path);
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                    }
                    resultSet.close();
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            poolInst.footConnection(connection);
        }
    }

    @Override
    public List<Request> findCreatedRequests() {
        return findRequestsByStatus(sql.getProperty(SqlService.SQL_GET_CREATED_REQUEST));
    }

    @Override
    public List<Request> findStaffedRequests() {
        return findRequestsByStatus(sql.getProperty(SqlService.SQL_GET_STAFFED_REQUEST));
    }

    @Override
    public List<Request> findConfirmedRequests() {
        return findRequestsByStatus(sql.getProperty(SqlService.SQL_GET_CONFIRMED_REQUEST));
    }

    @Override
    public Request findRequestById(int id) {
        Connection connection = poolInst.getConnection();
        Request request = new Request();
        try{
            PreparedStatement statement = connection.prepareStatement(sql.getProperty(SqlService.SQL_GET_REQUEST_BY_ID));
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            request.setId(id);
            request.setName(resultSet.getString(2));
            if (resultSet.getInt(3) == 0){
                request.setType(UserType.PHYSICAL);
            }else {
                request.setType(UserType.LEGAL);
            }
            request.setAmount(resultSet.getInt(4));
            resultSet.close();
            statement.close();
            statement = connection.prepareStatement(sql.getProperty(SqlService.SQL_GET_ATTACHMENT_BY_REQUEST_ID));
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                request.getAttachments().add(resultSet.getString(2));
            }
            resultSet.close();
            statement.close();
        }catch (SQLException exc){
            exc.printStackTrace();
        }finally {
            poolInst.footConnection(connection);
        }
        return request;
    }

    @Override
    public List<Request> findAnalyzedRequests() {
        return findRequestsByStatus(sql.getProperty(SqlService.SQL_GET_ANALYZED_REQUEST));
    }

    private List<Request> findRequestsByStatus(String sql){
        Connection connection = poolInst.getConnection();
        List<Request> requests = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Request request = new Request();
                request.setId(resultSet.getInt(1));
                request.setName(resultSet.getString(2));
                request.setAmount(resultSet.getInt(6));
                if (resultSet.getInt(5) == 0){
                    request.setType(UserType.PHYSICAL);
                }else {
                    request.setType(UserType.LEGAL);
                }
                requests.add(request);
            }
            resultSet.close();
            statement.close();
        }catch (SQLException exc){
            exc.printStackTrace();
        }finally {
            poolInst.footConnection(connection);
        }
        return requests;
    }

    @Override
    public void delete(int id) {
        Connection connection = poolInst.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(sql.getProperty(SqlService.SQL_DELETE_REQUEST));
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        }catch (SQLException exc){
            exc.printStackTrace();
        }finally {
            poolInst.footConnection(connection);
        }
    }
}
