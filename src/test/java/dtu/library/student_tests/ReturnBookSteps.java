package dtu.library.student_tests;

import static org.junit.Assert.assertFalse;

import dtu.library.acceptance_tests.ErrorMessageHolder;
import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReturnBookSteps {
    private LibraryApp library;
    private ErrorMessageHolder errorMessageHolder;

    public ReturnBookSteps (LibraryApp library, ErrorMessageHolder errorMessageHolder){
        this.library = library;
        this.errorMessageHolder = errorMessageHolder;
    }
    
    @When("the user with CPR {string} returns the book with the signature {string}")
        public void theUserWithCPRReturnsTheBookWithTheSignature(String cpr, String signature) {
        try {
            library.returnBook(cpr, signature);
        } catch (OperationNotAllowedException e){
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the book with signature {string} is no longer borrowed by the user with CPR {string}")
    public void theBookWithSignatureIsNoLongerBorrowedByTheUserWithCPR(String signature, String cpr) {
        assertFalse(library.isBorrowed(cpr, signature));
    }
    
}
