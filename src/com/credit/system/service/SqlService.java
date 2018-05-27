package com.credit.system.service;



import com.credit.system.connectionPool.DbConnectionPool;

import java.util.ResourceBundle;

/**
 * Service that returns queries to the database.
 *
 * @author Ivan Tkachev
 */
public class SqlService {

    private static SqlService instance;
    private static ResourceBundle bundle;


    private static final String SQL_FILE = "sql";

    public static final String SQL_GET_USER =           "SQL_GET_USER";
    public static final String SQL_SELECT_PRODUCT =             "SQL_SELECT_PRODUCT";



    private SqlService(){}

    /**
     * Method that returns instance of SqlService. This need to get queries to database.
     *
     * @return SqlService instance
     */
    public static SqlService getInstance(){
        if(instance == null){
            synchronized (DbConnectionPool.class){
                instance = new SqlService();
                bundle = ResourceBundle.getBundle(SQL_FILE);
            }
        }
        return instance;
    }
    /**
     * Method that returns query to database.
     *
     * @param key key that identify query
     *
     * @return query to database
     */
    public String getProperty(String key){
        return bundle.getString(key);
    }

}
