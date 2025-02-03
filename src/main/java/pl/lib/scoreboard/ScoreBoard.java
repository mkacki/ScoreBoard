package pl.lib.scoreboard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ScoreBoard {

    private static final String AWAY_TEAM_ALREADY_PLAYING = "Away team already playing another match";
    private static final String HOME_TEAM_ALREADY_PLAYING = "Home team already playing another match";

    private final Set<String> playing;
    private final Map<String, Match> contents;

    public ScoreBoard() {
        playing = new HashSet<>();
        contents = new HashMap<>();
    }

    public void addMatch(String home, String away) {
        if (playing.contains(home)) {
            throw new IllegalArgumentException(HOME_TEAM_ALREADY_PLAYING);
        } else
        if (playing.contains(away)) {
            throw new IllegalArgumentException(AWAY_TEAM_ALREADY_PLAYING);
        }

        contents.put(home, new Match(home, away));

        playing.add(home);
        playing.add(away);
    }

    public void update(String home, String away, int homeScore, int awayScore) {
        contents.get(getKey(home, away)).updateScore(homeScore, awayScore);
    }

    private String getKey(String home, String away) {
        return home + away;
    }

    String getMatchResult(String home, String away) {
        return contents.get(getKey(home, away)).getResult();
    }

    int size() {
        return contents.size();
    }
}
