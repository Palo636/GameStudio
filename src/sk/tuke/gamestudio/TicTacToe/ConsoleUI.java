package sk.tuke.gamestudio.TicTacToe;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleUI {

    boolean firstPlayer = true;
    private Field field;

    public ConsoleUI(Field field) {
        this.field = field;


    }

    public void newGameStarted(Field field) {
        this.field = field;
        for (int i = 1; i < 2; i++) {
            for (int j = 1; j < 2; j++) {
                field.getTiles(i, j).setState(Tile.State.EMPTY);
            }
        }
        do {
            update();
            processInput();
            if (isSolved()){
                System.out.println("ktosi vyhral");
            } else if(isFull()) {
                System.out.println("je remiza");
            }

        } while (true);

    }

    private void update() {
        System.out.println("\n");
        for (int i = 0; i < field.getRowCount(); i++) {
            for (int j = 0; j < field.getColumnCount(); j++) {
                if (field.getTiles(i, j).getState().equals(Tile.State.EMPTY)) {
                    System.out.print("- ");
                }
                if (field.getTiles(i, j).getState().equals(Tile.State.SECOND)) {
                    System.out.print("O ");
                }
                if (field.getTiles(i, j).getState().equals(Tile.State.FIRST)) {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }

    }

    public void changePlayer() {
        firstPlayer = firstPlayer == true ? false : true;
    }

    private void processInput() {
        String input = new Scanner(System.in).nextLine().trim();
        Pattern pattern = Pattern.compile("([1-3])([1-3])");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            int row = Integer.parseInt(matcher.group(1));
            int column = Integer.parseInt(matcher.group(2));
            if (field.getTiles(row - 1, column - 1).getState().equals(Tile.State.EMPTY)) {
                Tile.State state = firstPlayer == true ? Tile.State.FIRST : Tile.State.SECOND;

                field.getTiles(row - 1, column - 1).setState(state);
                changePlayer();
            } else {
            }
            System.out.println("\ntile je stavu" + field.getTiles(row - 1, column - 1).getState());
            System.out.println("\nplayer je " + firstPlayer);
        }
    }

    public boolean isFull() {
        boolean isFull = true;

        for (int i = 0; i < field.getRowCount(); i++) {
            for (int j = 0; j < field.getColumnCount(); j++) {
                if (field.getTiles(i, j).getState().equals(Tile.State.EMPTY)) {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    private boolean checkRowCol(Tile t1, Tile t2, Tile t3) {
        return (!t1.getState().equals(Tile.State.EMPTY) && t1.getState().equals(t2.getState()) && t2.getState().equals(t3.getState()));
    }

    private boolean checkDiagonalsForWin() {
        return ((checkRowCol(field.getTiles(0, 0), field.getTiles(2, 2), field.getTiles(2, 2))));
    }

    private boolean checkColumsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(field.getTiles(0, i), field.getTiles(1, i), field.getTiles(2, i)) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(field.getTiles(i, 0), field.getTiles(i, 1), field.getTiles(i, 2)) == true) {
                return true;
            }
        }
        return false;
    }

    public boolean isSolved() {
        return (checkColumsForWin() || checkRowsForWin() || checkDiagonalsForWin());
    }
}
