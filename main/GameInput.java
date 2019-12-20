package main;

import hero.Hero;
import java.util.ArrayList;

public class GameInput {

    private ArrayList<String> matrix;
    private ArrayList<Hero> players;
    private ArrayList<String> moves;
    private int noRounds = 0;
    private int noPlayers = 0;

    public final ArrayList<String> getMatrix() {
        return matrix;
    }

    public final void setMatrix(final ArrayList<String> matrix) {
        this.matrix = matrix;
    }

    public final ArrayList<Hero> getPlayers() {
        return players;
    }

    public final void setPlayers(final ArrayList<Hero> players) {
        this.players = players;
    }

    public final ArrayList<String> getMoves() {
        return moves;
    }

    public final void setMoves(final ArrayList<String> moves) {
        this.moves = moves;
    }

    public final void setNoPlayers(final int noPlayers) {
        this.noPlayers = noPlayers;
    }

    public final void setNoRounds(final int noRounds) {
        this.noRounds = noRounds;
    }

    public GameInput(final ArrayList<String> matrix, final ArrayList<Hero> players,
                     final ArrayList<String> moves, final int noPlayers, final int noRounds) {
        setMatrix(matrix);
        setPlayers(players);
        setMoves(moves);
        setNoPlayers(noPlayers);
        setNoRounds(noRounds);
    }
}
