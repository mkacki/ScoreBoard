package pl.lib.scoreboard;

class Match {

    private final String homeTeam;
    private final String awayTeam;

    private int homeScore;
    private int awayScore;

    Match(String homeTeam, String awayTeam ) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
    }

    String getResult(){
        return String.format("%s %d - %s %d", homeTeam, homeScore, awayTeam, awayScore);
    }
}
