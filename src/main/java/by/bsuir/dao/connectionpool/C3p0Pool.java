package by.bsuir.dao.connectionpool;

import by.bsuir.dao.exception.ConnectionPoolException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import lombok.extern.log4j.Log4j;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.locks.ReentrantLock;

@Log4j
public class C3p0Pool implements ConnectionPool {
    private static final String DB_PROPERTIES_PATH = "mysqldb";
    private static final String DB_DRIVER = "db.driver";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";

    private static final ConnectionPool instance = new C3p0Pool();
    private static ReentrantLock lock = new ReentrantLock();
    private ComboPooledDataSource cp;
    private boolean isInit = false;

    private C3p0Pool(){}

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void init() throws ConnectionPoolException {
        try {
            lock.lock();
            if(!isInit){
                ResourceBundle dbProp = ResourceBundle.getBundle(DB_PROPERTIES_PATH);

                try {
                    cp = new ComboPooledDataSource();
                    cp.setDriverClass(dbProp.getString(DB_DRIVER));
                    cp.setJdbcUrl(dbProp.getString(DB_URL));
                    cp.setUser(dbProp.getString(DB_USER));
                    cp.setPassword(dbProp.getString(DB_PASSWORD));

                    cp.setInitialPoolSize(1);
                    cp.setMaxStatements(30);
                    cp.setMaxStatementsPerConnection(30);
                    cp.setMinPoolSize(1);
                    cp.setAcquireIncrement(1);
                    cp.setMaxPoolSize(2);
                    cp.setMaxIdleTime(30);

                    isInit = true;
                    log.info("Connection pool was successfully initialized");
                } catch(PropertyVetoException e){
                    throw new ConnectionPoolException("Init connection pool fail", e);
                }
            } else {
                throw new ConnectionPoolException("Try to init already init pool");
            }
        } finally {
            lock.unlock();
        }
    }

    public Connection getConnection() throws ConnectionPoolException {
        try {
            return cp.getConnection();
        } catch(SQLException e){
            throw new ConnectionPoolException("Error while getting connection");
        }
    }

    public void destroy() throws ConnectionPoolException {
        try {
            lock.lock();
            if(isInit){
                try {
                    DataSources.destroy(cp);

                    isInit = false;
                    log.info("Connection pool was successfully destroyed");
                } catch(SQLException e){
                    throw new ConnectionPoolException("Error while destroying connection pool");
                }
            } else {
                throw new ConnectionPoolException("Try to dispose not init pool");
            }
        } finally {
            lock.unlock();
        }
//        cp.close();
    }
}
