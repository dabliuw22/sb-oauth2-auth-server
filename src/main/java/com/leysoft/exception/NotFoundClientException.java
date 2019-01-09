
package com.leysoft.exception;

public class NotFoundClientException extends RuntimeException {

    private static final long serialVersionUID = 7113323051014517321L;

    public NotFoundClientException() {
        super();
    }

    public NotFoundClientException(String message) {
        super(message);
    }

    public NotFoundClientException(Throwable cause) {
        super(cause);
    }
}
