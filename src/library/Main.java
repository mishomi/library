package library;

import library.inventory.Book;
import library.inventory.EBook;
import library.inventory.Genre;
import library.inventory.Movie;
import library.organization.Library;
import library.organization.Publisher;
import library.person.Author;
import library.person.Customer;
import library.transaction.BookingService;
import library.workers.Custodian;
import library.workers.Receptionist;
import library.workers.Supervisor;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        Genre genre = new Genre("basic_genre");
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

        Customer customer = new Customer("bob", value2, 20);

        library.addCustomer(customer);

        bookingService.book(customer, book);
        bookingService.book(customer, movie);
        bookingService.book(customer, eBook);

        bookingService.returnItem(customer, book);
        bookingService.returnItem(customer, movie);
        bookingService.returnItem(customer, eBook);
        System.out.println(BookingService.getOutstandingItemsCount());
        BookingService.getReCords();
    }
}
