package by.bsuir.dao.connectionpool;

import by.bsuir.dao.exception.ConnectionPoolException;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

@Log4j
public class ConnectionPoolImpl {
    private static final String DB_PROPERTIES_PATH = "mysqldb";
    private static final String DB_DRIVER = "db.driver";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_POOL_SIZE = "db.poolsize";

    private static final ConnectionPoolImpl instance = new ConnectionPoolImpl();
    private BlockingQueue<Connection> freeConnections;
    private BlockingQueue<Connection> busyConnections;
    private static ReentrantLock lock = new ReentrantLock();
    private volatile boolean isInit = false;

    private ConnectionPoolImpl(){}

    public static ConnectionPoolImpl getInstance() {
        return instance;
    }

    public void init() throws ConnectionPoolException {
        try {
            lock.lock();
            if(!isInit){
                ResourceBundle dbProp = ResourceBundle.getBundle(DB_PROPERTIES_PATH);
                int poolSize = Integer.parseInt(dbProp.getString(DB_POOL_SIZE));
                freeConnections = new ArrayBlockingQueue<>(poolSize);
                busyConnections = new ArrayBlockingQueue<>(poolSize);

                try {
                    Class.forName(dbProp.getString(DB_DRIVER));

                    for(int i = 0; i < poolSize; i++){
                        Connection connection = DriverManager.getConnection(
                                dbProp.getString(DB_URL),
                                dbProp.getString(DB_USER),
                                dbProp.getString(DB_PASSWORD)
                        );
                        connection.setAutoCommit(false);
                        freeConnections.add(connection);
                    }
                    isInit = true;

                    log.info("Connection pool was successfully initialized");
                } catch(ClassNotFoundException e){
                    throw new ConnectionPoolException("Database driver not found", e);
                } catch(SQLException e){
                    throw new ConnectionPoolException("Connection pool initialization fail", e);
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
            Connection connection = freeConnections.take();
            busyConnections.add(connection);
            return connection;
        } catch(InterruptedException e){
            throw new ConnectionPoolException("Taking interrupted connection", e);
        }
    }

    public void returnConnection(Connection connection) throws ConnectionPoolException {
        if(busyConnections.contains(connection)){
            try {
                busyConnections.remove(connection);
                freeConnections.put(connection);
            } catch(InterruptedException e){
                throw new ConnectionPoolException("Can't free connection", e);
            }
        } else {
            throw new ConnectionPoolException("Try to close not a pool connection");
        }
    }

    public void dispose() throws ConnectionPoolException {
        try {
            lock.lock();
            if(isInit){
                try {
                    for(Connection connection : freeConnections){
                        connection.close();
                    }
                    for(Connection connection : busyConnections){
                        connection.close();
                    }
                    isInit = false;

                    log.info("Connection pool was successfully destroyed");
                } catch(SQLException e){
                    throw new ConnectionPoolException("Connection pool dispose fail", e);
                }
            } else {
                throw new ConnectionPoolException("Try to dispose not init pool");
            }
        } finally {
            lock.unlock();
        }
    }
}