package com.solvd.library;

import com.solvd.library.organization.Library;
import com.solvd.library.organization.Publisher;
import com.solvd.library.person.Author;
import com.solvd.library.person.Customer;
import com.solvd.library.reflection.LibraryReflection;
import com.solvd.library.transaction.*;
import com.solvd.library.workers.Custodian;
import com.solvd.library.workers.Receptionist;
import com.solvd.library.workers.Supervisor;
import com.solvd.library.inventory.*;

import java.math.BigDecimal;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Genre genre = new Genre("basic_genre", GenreType.THRILLER);
        Publisher publisher = new Publisher("publisherName", "Georgia");
        BigDecimal value1 = new BigDecimal("12.25");
        BigDecimal value2 = new BigDecimal("129.25");

        Author author1 = new Author("fiodor dostoevsky", "Russia");
        Author author2 = new Author("JRR tolkien", "england");
        Author author3 = new Author("roger allers", "united states");

        Library library = new Library("main library");
        BookingService bookingService = new BookingService();

        Supervisor supervisor = new Supervisor(10000, "john");
        Receptionist receptionist = new Receptionist(3000, "anna", "English, Georgian");
        Custodian custodian = new Custodian(2500, "jerry", 1);

        library.addWorker(supervisor);
        library.addWorker(receptionist);
        library.addWorker(custodian);

        Book book = new Book("crime and punishment", author1, supervisor, value1, publisher, genre);
        Movie movie = new Movie("The lion king", author3, value1, supervisor, 6, publisher, genre);
        EBook eBook = new EBook("The lord of the rings", author2, supervisor, "download_link", publisher, genre);

        library.addInventoryItem(book);
        library.addInventoryItem(movie);
        library.addInventoryItem(eBook);

        supervisor.addInventoryItem(book);
        supervisor.addInventoryItem(movie);
        supervisor.addInventoryItem(eBook);

        Customer customer = new Customer("john", value2, 20, library);

        Runnable startup = () -> System.out.println("Starting library simulatin");
        startup.run();

        Supplier<String> welcomeMessage = () -> "Welcome to the library";
        System.out.println(welcomeMessage.get());

        Consumer<Inventory> printInventoryItem = item -> System.out.println("Inventory item: " + item.getName());
        library.getInventory().forEachItem(printInventoryItem);

        Predicate<Inventory> isPricedItemOver10 = item -> item instanceof ItemWithPrice && ((ItemWithPrice) item).getPrice().compareTo(new BigDecimal("10")) > 0;

        System.out.println("Items that costs above 10:");
        library.getInventory().filterItems(isPricedItemOver10).forEach(item -> System.out.println(item.getName()));

        Function<Inventory, String> inventoryDescription = item -> item.getName() + " by " + item.getAuthor().getName();

        System.out.println("Descriptions:");
        library.getInventory().mapItems(inventoryDescription).forEach(System.out::println);

        BiConsumer<Customer, Inventory> borrowingMessage = (cust, item) -> System.out.println(cust.getName() + " is about to borrow " + item.getName());
        borrowingMessage.accept(customer, book);

        System.out.println("item names: " +
                library.getInventory().getItems().stream()
                        .map(Inventory::getName)
                        .collect(Collectors.joining(", ")));

        System.out.println("Expensive items: " +
                library.getInventory().getItems().stream()
                        .filter(item -> item instanceof ItemWithPrice && ((ItemWithPrice) item).getPrice().compareTo(new BigDecimal("10")) > 0)
                        .map(Inventory::getName)
                        .toList());

        System.out.println("Sorted item names: " +
                library.getInventory().getItems().stream()
                        .map(Inventory::getName)
                        .sorted()
                        .toList());

        System.out.println("Inventory count: " + library.getInventory().getItems().stream().count());

        System.out.println("Customer names:");
        library.getCustomers().getItems().stream()
                .map(Customer::getName)
                .forEach(System.out::println);

        System.out.println("Library main.java.com.solvd.library.inventory empty: " + library.isInventoryEmpty());
        System.out.println("Genre empty: " + genre.getBooksInThisGenre().isEmpty());
        System.out.println("Publisher empty: " + publisher.getPublishedBooks().isEmpty());
        System.out.println("Booking records empty: " + bookingService.getOutstandingItemsCount());
        System.out.println("Library main.java.com.solvd.library.inventory size: " + library.getInventorySize());
        System.out.println("Workers size: " + library.getWorkersSize());
        System.out.println("Customers size: " + library.getCustomersSize());
        System.out.println("Genre size: " + genre.getBooksInThisGenre().size());
        System.out.println("Publisher size: " + publisher.getPublishedBooks().size());
        System.out.println("First library item: " + library.getFirstInventoryItem().getName());
        System.out.println("First genre item: " + genre.getFirstItem().getName());
        System.out.println("First publisher item: " + publisher.getFirstItem().getName());


        BorrowValidator validator = (cust, item) ->
                cust.getMoney().compareTo(item.getPrice()) >= 0;

        System.out.println("can " + customer.getName() + " borrow: " + book.getName() + "?" + validator.canBorrow(customer, book));

        NotificationService notificationService = (cust, message) ->
                System.out.println("Notifying " + cust.getName() + ": " + message);

        notificationService.notify(customer, "Your book is overdue!");

        for (Inventory item : library.getInventory().getItems()) {
            System.out.println(item.getName());
        }

        for (Inventory item : genre.getBooksInThisGenre()) {
            System.out.println(item.getName());
        }

        for (Inventory item : publisher.getPublishedBooks()) {
            System.out.println(item.getName());
        }
        BorrowRecord borrowRecord = new BorrowRecord(
                customer.getName(),
                book.getName(),
                java.time.LocalDateTime.now().toString()
        );
        System.out.println(borrowRecord);

        LibraryReflection.inspectClass(Book.class);
        LibraryReflection.handleCustomAnnotations(Book.class);
        LibraryReflection.handleCustomAnnotations(Library.class);
        LibraryReflection.createObjectAndCallMethodUsingReflection();

        try (LibrarySession librarySession = new LibrarySession("session 1")) {
            bookingService.book(customer, book);
            bookingService.book(customer, movie);
            bookingService.book(customer, eBook);

            bookingService.returnItem(customer, book);
            bookingService.returnItem(customer, movie);
            bookingService.returnItem(customer, eBook);

            librarySession.log("Finished transactions successfully");
        } catch (ItemUnavailableException e) {
            System.out.println("Checked exception handled: " + e.getMessage());
        } finally {
            System.out.println("completed execution");
        }
    }
}