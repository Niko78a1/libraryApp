package dtu.library.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;

public class User {
    private String cpr;
    private String name;
    private String eMail;
    private String address;
    private Integer postcode;
    private String city;
    // Gamle kode: private List<Book> borrowedBooks = new ArrayList<>(); // Liste med lånte bøger
    private Map <Book, Calendar> borrowedBooks = new HashMap<>();

    // Konstruktør
    public User(String cpr, String name, String eMail) {
        this.cpr = cpr;
        this.name = name;
        this.eMail = eMail;
    }

    // Getter for cpr
    public String getCpr() {
        return cpr;
    }

    // Getter for navn
    public String getName() {
        return name;
    }

    // Getter for e-mail
    public String getEmail() {
        return eMail;
    }

    // Setter for adresse
    public void setAddress(String address) {
        this.address = address;
    }

    // Setter for post kode
    public void setPostCode(Integer postCode) {
        this.postcode = postCode;
    }

    // Setter for by
    public void setCity(String city) {
        this.city = city;
    }

    public void borrowNewBook(Book book, Calendar date) {
        borrowedBooks.put(book, date);
    }

    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks.keySet());
    }

    public Calendar getBorrowDate(Book book) {
        return borrowedBooks.get(book); // Henter datoen for et specifikt lån
    }

    public void returnBook(String signature) {
        Book bookToReturn = null;

        // 1. Find bogen i listen af lånte bøger
        for (Book b : borrowedBooks.keySet()) {
            if (b.getSignature().equals(signature)) {
                bookToReturn = b;
                break; // Stop loopet, vi har fundet bogen
            }
        }

        // 2. slet bogen, hvis vi fandt den
        if (bookToReturn != null) {
            borrowedBooks.remove(bookToReturn);
        }
    }
}
