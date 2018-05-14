package sk.tuke.gamestudio.TicTacToe;

public class TicTacToe {

    public static void main(String[] args) {
        Field field = new Field(3, 3);
        ConsoleUI ui = new ConsoleUI(field);
        ui.newGameStarted(field);


    }
}
