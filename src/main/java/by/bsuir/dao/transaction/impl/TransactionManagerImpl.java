package by.bsuir.dao.transaction.impl;

import by.bsuir.dao.connectionpool.BaseDataSource;
import by.bsuir.dao.connectionpool.C3p0Pool;
import by.bsuir.dao.connectionpool.ConnectionPoolImpl;
import by.bsuir.dao.exception.ConnectionPoolException;
import by.bsuir.dao.exception.DAOException;
import by.bsuir.dao.transaction.TransactionManager;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Callable;

@Log4j
public class TransactionManagerImpl extends BaseDataSource implements TransactionManager {
    private static final TransactionManager instance = new TransactionManagerImpl();
    private static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    private TransactionManagerImpl(){}

    public static TransactionManager getInstance(){
        return instance;
    }

    @Override
    public <T> T doInTransaction(Callable<T> unitOfWork) throws DAOException {
        Connection connection = null;
        try {
//            connection = C3p0Pool.getInstance().getConnection();
            connection = ConnectionPoolImpl.getInstance().getConnection();
//            connection.setAutoCommit(false);
            connectionHolder.set(connection);

            T result = unitOfWork.call();

            connection.commit();
            return result;
        } catch(Exception exception){
            if(connection != null){
                try {
                    connection.rollback();
                } catch(SQLException sqlException){
                    log.error(sqlException);
                }
            }
            throw new DAOException(exception);
        } finally {
            if(connection != null){
                try {
//                    connection.setAutoCommit(true);
//                    connection.close();
                    ConnectionPoolImpl.getInstance().returnConnection(connection);
                } catch(ConnectionPoolException poolException){
                    log.error(poolException);
                }
                connectionHolder.remove();
            }
        }
    }

    @Override
    public Connection getConnection() {
        return connectionHolder.get();
    }
}
