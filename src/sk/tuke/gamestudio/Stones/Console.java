package sk.tuke.gamestudio.Stones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Console {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private Field field;
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public void newGameStarted(Field field) {
        do {
            this.field = field;
            update();
            processInput(readLine());
            if (field.isSolved()) {
                System.out.println("CONGRATS!!! Vyhral si za: " + Stones.getInstance().getPlayingSeconds() + " sec");
                System.exit(0);
            } else {
            }
        } while (true);
    }

    public void update() {
        for (int i = 0; i < field.getRowCount(); i++) {
            for (int j = 0; j < field.getColumnCount(); j++) {
                if (field.getStone(i, j).getValue() != 0) {
                    if (field.getStone(i, j).getValue() > 9) {
                        System.out.print("  " + field.getStone(i, j).getValue());
                    } else {
                        System.out.print("   " + field.getStone(i, j).getValue());
                    }
                } else {
                    System.out.print(ANSI_RED + "   X" + ANSI_RESET);
                }
            }
            System.out.println("");


        }
        System.out.println("GAME TIME IS: " + Stones.getInstance().getPlayingSeconds() + "SEC");
    }

    private void processInput(String input) {
        switch (input.toUpperCase()) {
            case "W":
                field.moveStone(Direction.UP);
                break;
            case "S":
                field.moveStone(Direction.DOWN);
                break;
            case "A":
                field.moveStone(Direction.LEFT);
                break;
            case "D":
                field.moveStone(Direction.RIGHT);
                break;
            case "SAVE":
                field.save();
                break;
            case "X":
                System.exit(0);
                break;
        }
    }

}
