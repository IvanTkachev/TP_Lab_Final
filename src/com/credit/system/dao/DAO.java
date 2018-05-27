package com.credit.system.dao;


import com.credit.system.connectionPool.DbConnectionPool;
import com.credit.system.service.SqlService;

/**
 * The class that initializes {@link DbConnectionPool} and {@link SqlService}
 *
 * @author Ivan Tkachev
 */
public abstract class DAO {

    protected static DbConnectionPool poolInst;
    protected static SqlService sql;

    protected DAO() {
        poolInst = DbConnectionPool.getInstance();
        sql = SqlService.getInstance();
    }
}
