package dtu.library.student_tests;

import dtu.library.acceptance_tests.ErrorMessageHolder;
import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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

}