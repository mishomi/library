package inventory;

public class NotOldEnoughException extends RuntimeException {

    public NotOldEnoughException(String message) {
        super(message);
    }
}