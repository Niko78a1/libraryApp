package dtu.library.student_tests;

import dtu.library.acceptance_tests.ErrorMessageHolder;
import dtu.library.app.LibraryApp;
import dtu.library.app.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class UserSteps {

    private LibraryApp libraryApp;
    private User user; 

    // Konstruktør m. injection
    public UserSteps(LibraryApp libraryApp) {
		this.libraryApp = libraryApp;
	}

    @Given("there is a user with CPR {string}, name {string}, e-mail {string}")
    public void thereIsAUserWithCPRNameEMail(String cpr, String name, String eMail) {
        this.user = new User(cpr, name, eMail);
    }

    @Given("the user has address street {string}, post code {int}, and city {string}")
    public void theUserHasAddressStreetPostCodeAndCity(String address, Integer postCode, String city) {
        this.user.setAddress(address);
        this.user.setPostCode(postCode);
        this.user.setCity(city);
    }

    @When("the administrator registers the user")
    public void theAdministratorRegistersTheUser() {
        libraryApp.registerUser(this.user);
    }

    @Then("the user is a registered user of the library")
    public void theUserIsARegisteredUserOfTheLibrary() {
        assertTrue(libraryApp.isUser(this.user));
    }
    
}
