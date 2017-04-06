package by.bsuir.service.impl;

import by.bsuir.dao.exception.DAOException;
import by.bsuir.dao.transaction.impl.TransactionManagerImpl;
import by.bsuir.service.exception.ServiceException;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;

import java.util.concurrent.Callable;

@Log4j
public abstract class BaseService {

    public final <V> V service(Callable<V> callable) throws ServiceException {
        try {
            return TransactionManagerImpl.getInstance().doInTransaction(callable);
        } catch(DAOException e){
            log.error(e);
            throw new ServiceException(e);
        }
    }
}
