package transaction;

import person.Customer;

@FunctionalInterface
public interface NotificationService {
    void notify(Customer customer, String message);
}