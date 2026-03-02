package dtu.library.app;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String cpr;
    private String name;
    private String eMail;
    private String address;
    private Integer postcode;
    private String city;
    private List<Book> borrowedBooks = new ArrayList<>(); // Liste med lånte bøger


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

    public void borrowNewBook(Book book) {
        borrowedBooks.add(book);
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
