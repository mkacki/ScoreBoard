package pl.lib.scoreboard;

class Match {

    private final String homeTeam;
    private final String awayTeam;

    private int homeScore;
    private int awayScore;

    Match(String homeTeam, String awayTeam ) {
        if (homeTeam == null || awayTeam == null) {
            throw new NullPointerException("Required parameter missing");
        } else
        if (homeTeam.trim().isEmpty()|| awayTeam.trim().isEmpty()) {
            throw new IllegalArgumentException("Required parameter is blank");
        } else if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Same home and away team");
        }

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public void updateScore(int homeScore, int awayScore) {
        if (this.homeScore > homeScore || this.awayScore > awayScore) {
            throw new IllegalArgumentException("Illegal score update");
        }
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    String getResult(){
        return String.format("%s %d - %s %d", homeTeam, homeScore, awayTeam, awayScore);
    }
}
