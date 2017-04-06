package by.bsuir.dao.exception;

import by.bsuir.dao.transaction.TransactionManager;

public class NonTransactionInvocationException extends RuntimeException {
    private static long serialVersionUID = 1L;

    public NonTransactionInvocationException() {
    }

    public NonTransactionInvocationException(String message) {
        super(message);
    }

    public NonTransactionInvocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonTransactionInvocationException(Throwable cause) {
        super(cause);
    }
}
