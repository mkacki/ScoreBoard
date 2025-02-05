package pl.lib.scoreboard;

import java.util.*;
import java.util.stream.Collectors;

public class ScoreBoard {

    private static final String AWAY_TEAM_ALREADY_PLAYING = "Away team is already playing another match";
    private static final String HOME_TEAM_ALREADY_PLAYING = "Home team is already playing another match";
    private static final String NO_SUCH_MATCH = "No such match on scoreboard";

    private final Set<String> teamsOnScoreBoard;
    private final Map<String, Match> contents;

    public ScoreBoard() {
        teamsOnScoreBoard = new HashSet<>();
        contents = new HashMap<>();
    }

    public void addMatch(String home, String away) {
        if (teamsOnScoreBoard.contains(home)) {
            throw new IllegalArgumentException(HOME_TEAM_ALREADY_PLAYING);
        } else
        if (teamsOnScoreBoard.contains(away)) {
            throw new IllegalArgumentException(AWAY_TEAM_ALREADY_PLAYING);
        }

        contents.put(getKey(home,away), new Match(home, away));

        teamsOnScoreBoard.add(home);
        teamsOnScoreBoard.add(away);
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

    public void finishMatch(String home, String away) {
        String key = getKey(home, away);
        if (contents.containsKey(key)) {
            teamsOnScoreBoard.remove(home);
            teamsOnScoreBoard.remove(away);
            contents.remove(key);
        } else {
            throw new IllegalArgumentException(NO_SUCH_MATCH);
        }
    }

    public List<Match> getSummary(){
        return contents.values().stream().sorted((o1, o2) -> {
            int result;
            if (o1.getTotalScore() == o2.getTotalScore()) {
                result = o2.getSequence() - o1.getSequence();
            } else {
                result = o2.getTotalScore() - o1.getTotalScore();
            }
            return result;
        }).collect(Collectors.toCollection(ArrayList::new));
    }
}
