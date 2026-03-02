package dtu.library.student_tests;

import dtu.library.acceptance_tests.ErrorMessageHolder;
import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.User;
import dtu.library.app.Book;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BorrowBookSteps {

    private LibraryApp library;
    private ErrorMessageHolder errorMessageHolder;

    public BorrowBookSteps(LibraryApp library, ErrorMessageHolder errorMessageHolder) {
        this.library = library;
        this.errorMessageHolder = errorMessageHolder;
    }

    @When("the user with CPR {string} borrows the book with the signature {string}")
    public void theUserWithCPRBorrowsTheBookWithTheSignature(String cpr, String signature) {
        try {
            library.borrowBook(cpr, signature);
        } catch (OperationNotAllowedException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the book with signature {string} is borrowed by the user with CPR {string}")
    public void theBookWithSignatureIsBorrowedByTheUserWithCPR(String signature, String cpr) {
        assertTrue(library.hasBorrowed(cpr, signature));
    }

    @Given("the user with CPR {string} has borrowed these books")
    public void theUserWithCPRHasBorrowedTheseBooks(String cpr, io.cucumber.datatable.DataTable dataTable) throws OperationNotAllowedException {
        for (List<String> row : dataTable.asLists(String.class)) {
            String title = row.get(0);
            String author = row.get(1);
            String signature = row.get(2);

            // 1. Opret bogen
            Book book = new Book(title, author, signature);

            // 2. tilføj til bibloteket
            library.addBook(book);

            // 3. Lån bogen ud til brugeren
            library.borrowBook(cpr, signature);
        }
    }

}