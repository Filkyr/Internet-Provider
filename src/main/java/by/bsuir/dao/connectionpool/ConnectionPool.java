package by.bsuir.dao.connectionpool;

import by.bsuir.dao.exception.ConnectionPoolException;

import java.sql.Connection;

public interface ConnectionPool {
    void init() throws ConnectionPoolException;
    void destroy() throws ConnectionPoolException;
    Connection getConnection() throws ConnectionPoolException;
}
