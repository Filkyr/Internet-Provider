package by.bsuir.dao.exception;

import by.bsuir.dao.SourceInit;
import by.bsuir.dao.connectionpool.ConnectionPoolImpl;

public class PoolInitException extends RuntimeException {
    private static long serialVersionUID = 1L;

    public PoolInitException() {
    }

    public PoolInitException(String message) {
        super(message);
    }

    public PoolInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolInitException(Throwable cause) {
        super(cause);
    }
}
