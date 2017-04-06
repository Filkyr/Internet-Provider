package by.bsuir.dao.exception;

import by.bsuir.dao.connectionpool.ConnectionPoolImpl;

public class ConnectionPoolException extends Exception {
    private static long serialVersionUID = 1L;

    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
