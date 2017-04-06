package by.bsuir.dao.transaction;

import by.bsuir.dao.exception.DAOException;

import java.util.concurrent.Callable;

public interface TransactionManager {
    <T> T doInTransaction(Callable<T> unitOfWork) throws DAOException;
}
