package by.bsuir.service.exception;

public class ServiceDataException extends Exception {
    private static long serialVersionUID = 1L;

    public ServiceDataException() {
        super();
    }

    public ServiceDataException(String message) {
        super(message);
    }

    public ServiceDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceDataException(Throwable cause) {
        super(cause);
    }
}
