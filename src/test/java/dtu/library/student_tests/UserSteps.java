package dtu.library.student_tests;

import dtu.library.acceptance_tests.ErrorMessageHolder;
import dtu.library.app.LibraryApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserSteps {

    private LibraryApp libraryApp;

    // Konstruktør m. injection
    public UserSteps(LibraryApp libraryApp) {
		this.libraryApp = libraryApp;
	}

    @Given("there is a user with CPR {string}, name {string}, e-mail {string}")
    public void thereIsAUserWithCPRNameEMail(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the user has address street {string}, post code {int}, and city {string}")
    public void theUserHasAddressStreetPostCodeAndCity(String string, Integer int1, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the administrator registers the user")
    public void theAdministratorRegistersTheUser() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user is a registered user of the library")
    public void theUserIsARegisteredUserOfTheLibrary() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
}
