package sk.tuke.gamestudio.TicTacToe;

public class Tile {

    enum State {
        FIRST,
        SECOND,
        EMPTY
    }

    private State state = State.EMPTY;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
