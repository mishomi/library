package com.solvd.library;

import com.solvd.library.organization.Library;
import com.solvd.library.organization.Publisher;
import com.solvd.library.person.Author;
import com.solvd.library.person.Customer;
import com.solvd.library.reflection.LibraryReflection;
import com.solvd.library.transaction.*;
import com.solvd.library.util.TextWordCounter;
import com.solvd.library.workers.Custodian;
import com.solvd.library.workers.Receptionist;
import com.solvd.library.workers.Supervisor;
import com.solvd.library.inventory.*;
import com.solvd.library.concurrent.LibraryConcurrencyDemo;
import com.solvd.library.async.LibraryAsyncDemo;

import java.math.BigDecimal;
import java.util.function.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        TextWordCounter.countUniqueWords();
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

        Runnable startup = () -> log.info("Starting library simulatin");
        startup.run();

        Supplier<String> welcomeMessage = () -> "Welcome to the library";
        log.info(welcomeMessage.get());

        Consumer<Inventory> printInventoryItem = item -> log.info("Inventory item: " + item.getName());
        library.getInventory().forEachItem(printInventoryItem);

        Predicate<Inventory> isPricedItemOver10 = item -> item instanceof ItemWithPrice && ((ItemWithPrice) item).getPrice().compareTo(new BigDecimal("10")) > 0;

        log.info("Items that costs above 10:");
        library.getInventory().filterItems(isPricedItemOver10).forEach(item -> log.info(item.getName()));

        Function<Inventory, String> inventoryDescription = item -> item.getName() + " by " + item.getAuthor().getName();

        log.info("Descriptions:");
        library.getInventory().mapItems(inventoryDescription).forEach(log::info);

        BiConsumer<Customer, Inventory> borrowingMessage = (cust, item) -> log.info(cust.getName() + " is about to borrow " + item.getName());
        borrowingMessage.accept(customer, book);

        log.info("item names: " +
                library.getInventory().getItems().stream()
                        .map(Inventory::getName)
                        .collect(Collectors.joining(", ")));

        log.info("Expensive items: " +
                library.getInventory().getItems().stream()
                        .filter(item -> item instanceof ItemWithPrice && ((ItemWithPrice) item).getPrice().compareTo(new BigDecimal("10")) > 0)
                        .map(Inventory::getName)
                        .toList());

        log.info("Sorted item names: " +
                library.getInventory().getItems().stream()
                        .map(Inventory::getName)
                        .sorted()
                        .toList());

        log.debug("Inventory count: " + library.getInventory().getItems().stream().count());

        log.info("Customer names:");
        library.getCustomers().getItems().stream()
                .map(Customer::getName)
                .forEach(log::info);

        log.debug("Publisher empty: " + publisher.getPublishedBooks().isEmpty());
        log.debug("Booking records empty: " + bookingService.getOutstandingItemsCount());
        log.debug("Library inventory size: " + library.getInventorySize());
        log.debug("Workers size: " + library.getWorkersSize());
        log.debug("Customers size: " + library.getCustomersSize());
        log.debug("Genre size: " + genre.getBooksInThisGenre().size());
        log.debug("Publisher size: " + publisher.getPublishedBooks().size());
        log.debug("First library item: " + library.getFirstInventoryItem().getName());
        log.debug("First genre item: " + genre.getFirstItem().getName());
        log.debug("First publisher item: " + publisher.getFirstItem().getName());
        log.debug("Library inventory empty: " + library.isInventoryEmpty());
        log.debug("Genre empty: " + genre.getBooksInThisGenre().isEmpty());


        BorrowValidator validator = (cust, item) ->
                cust.getMoney().compareTo(item.getPrice()) >= 0;

        log.warn("can " + customer.getName() + " borrow: " + book.getName() + "?" + validator.canBorrow(customer, book));

        NotificationService notificationService = (cust, message) ->
                log.warn("Notifying " + cust.getName() + ": " + message);

        notificationService.notify(customer, "Your book is overdue!");

        for (Inventory item : library.getInventory().getItems()) {
            log.info(item.getName());
        }

        for (Inventory item : genre.getBooksInThisGenre()) {
            log.info(item.getName());
        }

        for (Inventory item : publisher.getPublishedBooks()) {
            log.info(item.getName());
        }
        BorrowRecord borrowRecord = new BorrowRecord(
                customer.getName(),
                book.getName(),
                java.time.LocalDateTime.now().toString()
        );
        log.info(borrowRecord);

        LibraryConcurrencyDemo.runDemo();
        LibraryAsyncDemo.runDemo(library);

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
            log.error("Checked exception handled: " + e.getMessage());
        } finally {
            log.info("completed execution");
        }
    }
}