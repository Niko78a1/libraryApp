package dtu.library.student_tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.mockito.Mockito.verify;

import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.User;
import dtu.library.acceptance_tests.MockEmailHolder;
import dtu.library.app.EmailServer;

public class EmailSteps {

    private LibraryApp library;
    private EmailServer mockEmailServer;

    public EmailSteps(LibraryApp library, MockEmailHolder mockEmailHolder) {
        this.library = library;
        this.mockEmailServer = mockEmailHolder.getMockEmailServer();
    }

    @When("the administrator sends a reminder to all the users with overdue books")
        public void theAdministratorSendsAReminderToAllTheUsersWithOverdueBooks() {
        library.sendReminderToUsersOverdue();
    }

    @Then("the user with CPR {string} has received an email with the subject {string} and the text {string}")
    public void theUserWithCPRHasReceivedAnEmailWithTheSubjectAndTheText(String cpr, String subject, String text) throws OperationNotAllowedException {
        // find bruger via CPR
        User user = library.getUserByCpr(cpr);

        // Find brugerens mail
        String expectedMail = user.getEmail();

        // Verificer at mock'en blev kaldt med den rigtige email adresse
        verify(mockEmailServer).sendEmail(expectedMail, subject, text);
    }

}
