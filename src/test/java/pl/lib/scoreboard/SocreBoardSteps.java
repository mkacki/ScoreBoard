package pl.lib.scoreboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class SocreBoardSteps {

    private String home;
    private String away;
    private Match match;

    private ScoreBoard scoreBoard;
    private Exception thrownException;

    @Given("Two teams {string} and {string}")
    public void selectTeams(String home, String away) {
        this.home = home;
        this.away = away;
    }

    @When("I create create new match")
    public void newMatch(){
        try {
            match = new Match(getValue(home), getValue(away));
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("current result of that match is {string}")
    public void validResult(String expectedResult ){
        assertEquals(expectedResult, match.getResult());
    }

    @Given("new scoreboard")
    public void newScoreBoard(){
        this.scoreBoard = new ScoreBoard();
    }

    @When("I add match to scoreboard {string} - {string}")
    public void addMatch(String home, String away ){
        try {
            scoreBoard.addMatch(getValue(home), getValue(away));
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("scoreboard size is {int}")
    public void scoreboardSize(int expectedSize){
        assertEquals(expectedSize, scoreBoard.size());
    }

    @Then("It should throw an {string} - {string}")
    public void itShouldThrowAn(String expectedException, String expectedMessage) {
        assertNotNull(thrownException);
        assertEquals(expectedException, thrownException.getClass().getSimpleName());
        assertEquals(expectedMessage, thrownException.getMessage());
        thrownException = null;
    }

    private String getValue(String value) {
        String result = value;
        if (value == null || value.equals("null")) result = null;
        return result;
    }
}

