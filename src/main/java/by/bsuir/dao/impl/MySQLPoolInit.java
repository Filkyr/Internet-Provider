package by.bsuir.dao.impl;

import by.bsuir.dao.SourceInit;
import by.bsuir.dao.connectionpool.ConnectionPoolImpl;
import by.bsuir.dao.exception.PoolInitException;
import by.bsuir.dao.exception.ConnectionPoolException;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Log4j
@Repository("pool")
public class MySQLPoolInit implements SourceInit {

    @Override
    public void init() {
        ConnectionPoolImpl pool = ConnectionPoolImpl.getInstance();
//        ConnectionPool pool = C3p0Pool.getInstance();
        try {
            pool.init();
        } catch(ConnectionPoolException e){
            log.error(e);
            throw new PoolInitException("Connection pool initialization error", e);
        }
    }

    @Override
    public void destroy() {
        ConnectionPoolImpl pool = ConnectionPoolImpl.getInstance();
//        ConnectionPool pool = C3p0Pool.getInstance();
        try {
//            pool.destroy();
            pool.dispose();
        } catch(ConnectionPoolException e){
            log.error(e);
        }
    }
}
