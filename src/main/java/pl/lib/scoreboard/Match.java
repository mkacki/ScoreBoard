package pl.lib.scoreboard;

import java.util.concurrent.atomic.AtomicInteger;

public class Match {

    private static final String MISSING_PARAMETER = "Required parameter is missing";
    private static final String BLANK_PARAMETER = "Required parameter is blank";
    private static final String SAME_TEAM = "Same home and away team";
    private static final String INVALID_UPDATE = "Illegal score update";

    private static final AtomicInteger counter = new AtomicInteger();

    private final String homeTeam;
    private final String awayTeam;

    private final int homeScore;
    private final int awayScore;

    private final Integer sequence;

    Match(String homeTeam, String awayTeam ) {
        if (homeTeam == null || awayTeam == null) {
            throw new NullPointerException(MISSING_PARAMETER);
        } else
        if (homeTeam.trim().isEmpty()|| awayTeam.trim().isEmpty()) {
            throw new IllegalArgumentException(BLANK_PARAMETER);
        } else if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException(SAME_TEAM);
        }

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.sequence = counter.getAndIncrement();
    }

    Match(Match match, int homeScore, int awayScore) {
        if (match.homeScore > homeScore || match.awayScore > awayScore) {
            throw new IllegalArgumentException(INVALID_UPDATE);
        }
        this.homeTeam = match.homeTeam;
        this.awayTeam = match.awayTeam;
        this.sequence = match.sequence;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public String getResult(){
        return String.format("%s %d - %s %d", homeTeam, homeScore, awayTeam, awayScore);
    }

    Integer getSequence() {
        return sequence;
    }

    int getTotalScore() {
        return homeScore + awayScore;
    }
}
