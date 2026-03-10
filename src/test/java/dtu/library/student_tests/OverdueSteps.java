package dtu.library.student_tests;

import dtu.library.acceptance_tests.ErrorMessageHolder;
import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.User;
import dtu.library.app.Book;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class OverdueSteps {

    private LibraryApp library;

    // Konstruktør
    public OverdueSteps(LibraryApp library) {
        this.library = library;
    }

    @Then("the user with CPR {string} has overdue books")
        public void theUserWithCPRHasOverdueBooks(String cpr) {
        assertTrue(library.hasOverdueBooks(cpr));
    }

    @Then("the user with CPR {string} has to pay a fine of {int} DKK")
        public void theUserWithCPRHasToPayAFineOfDKK(String cpr, Integer fineAmount) {
        assertEquals(fineAmount.intValue(), library.getFine(cpr));
    }
    
}
