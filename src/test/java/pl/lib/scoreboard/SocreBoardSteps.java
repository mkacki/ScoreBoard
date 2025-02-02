package pl.lib.scoreboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class SocreBoardSteps {

    String home;
    String away;

    Match match;

    @Given("Two teams {string} and {string}")
    public void selectTeams(String home, String away) {
        this.home = home;
        this.away = away;
    }

    @When("I create create new match")
    public void newMatch(){
        match = new Match(home, away);
    }

    @Then("current result of that match is {string}")
    public void validResult(String expectedResult ){
        assertEquals(expectedResult, match.getResult());
    }
}

