package dtu.library.student_tests;

import dtu.library.acceptance_tests.MockDateHolder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class TimeSteps {

    private MockDateHolder mockDateHolder;

    // Konstruktør
    public TimeSteps(MockDateHolder mockDateHolder) {
        this.mockDateHolder = mockDateHolder;
    }

    @When("{int} days have passed")
    public void daysHavePassed(Integer days) {
        mockDateHolder.advanceDateByDays(days);
    }
    
}
