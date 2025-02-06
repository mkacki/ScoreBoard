package pl.lib.scoreboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;


public class ScoreBoardSteps {

    private String home;
    private String away;
    private Match match;

    private ScoreBoard scoreBoard;
    private Exception thrownException;

    @Given("two teams {string} and {string}")
    public void selectTeams(String home, String away) {
        this.home = home;
        this.away = away;
    }

    @When("we create new match")
    public void newMatch() {
        try {
            match = new Match(getValue(home), getValue(away));
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("current result of that match is {string}")
    public void validResult(String expectedResult) {
        assertEquals(expectedResult, match.getResult());
    }

    @Given("new scoreboard")
    public void newScoreBoard() {
        this.scoreBoard = new ScoreBoard();
    }

    @When("we add match to scoreboard {string} - {string}")
    public void addMatch(String home, String away) {
        try {
            scoreBoard.addMatch(getValue(home), getValue(away));
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("scoreboard size is {int}")
    public void scoreboardSize(int expectedSize) {
        assertEquals(expectedSize, scoreBoard.size());
    }

    @Then("it should throw an {string} - {string}")
    public void itShouldThrowAn(String expectedException, String expectedMessage) {
        assertNotNull(thrownException);
        assertEquals(expectedException, thrownException.getClass().getSimpleName());
        assertEquals(expectedMessage, thrownException.getMessage());
        thrownException = null;
    }

    @When("update game score {string} - {string} {int} {int}")
    public void updateMatch(String home, String away, int homeScore, int awayScore) {
        try {
            this.scoreBoard.update(home, away, homeScore, awayScore);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("match {string} - {string} result is {string}")
    public void matchResult(String home, String away, String matchResult) {
        assertEquals(matchResult, scoreBoard.getMatchResult(home, away));
    }

    private String getValue(String value) {
        String result = value;
        if (value == null || value.equals("null")) result = null;
        return result;
    }

    @When("finish match on scoreboard {string} - {string}")
    public void finishMatch(String home, String away) {
        try {
            this.scoreBoard.finishMatch(home, away);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("scoreboard correctly sorted by total score and most recent match")
    public void scoreboardCorrectlySorted() {

        String[] results = {
                "Uruguay 6 - Italy 6",
                "Spain 10 - Brazil 2",
                "Mexico 0 - Canada 5",
                "Argentina 3 - Australia 1",
                "Germany 2 - France 2"
        };

        List<Match> summary = scoreBoard.getSummary();

        for (int i = 0; i < results.length; i++) {
            assertEquals(results[i], summary.get(i).getResult());
        }
    }

    @When("Multithreaded scoreboard modification")
    public void multithreadedWritesToScoreboard() throws Exception {

        int executions = 1000000;
        CountDownLatch latch = new CountDownLatch(executions);
        ExecutorService executor = Executors.newFixedThreadPool(100);
        try {
            for (int i = 0; i < executions; i++) {
                executor.submit(() -> {
                    try {
                        for (int j = 0; j < 100; j++) {
                            scoreBoard.addMatch("Poland", "Turkey");
                            scoreBoard.update("Poland", "Turkey", 1, 1);
                            finishMatch("Poland", "Turkey");
                        }
                        scoreBoard.addMatch("Poland", "Turkey");
                    } finally {
                        latch.countDown();
                    }
                });
            }
            if (!latch.await(10, TimeUnit.SECONDS)) {
                throw new IllegalStateException("Test took too long to finish");
            }
        } finally {
            executor.shutdownNow();
        }

    }

    @Then("Row {int} Result: {string}")
    public void RowResult(int n, String result) {
        assertEquals(result, scoreBoard.getSummary().get(n).getResult());
    }


}

