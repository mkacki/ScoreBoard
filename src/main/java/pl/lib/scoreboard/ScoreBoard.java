package pl.lib.scoreboard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ScoreBoard {

    private static final String AWAY_TEAM_ALREADY_PLAYING = "Away team already playing another match";
    private static final String HOME_TEAM_ALREADY_PLAYING = "Home team already playing another match";

    private Set<String> playing;
    private Map<String, Match> contents;

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

    int size() {
        return contents.size();
    }
}
