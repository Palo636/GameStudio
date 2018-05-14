package sk.tuke.gamestudio.Minesweeper;


import sk.tuke.gamestudio.Minesweeper.consoleui.ConsoleUI;
import sk.tuke.gamestudio.Minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper {
    private static long startMillis;
    private static Minesweeper instance;



    private static Settings setting;

    /**
     * User interface.
     */
    private UserInterface userInterface;

    /**
     * Constructor.
     */
    public Minesweeper() {
        instance = this;
        userInterface = new ConsoleUI();
        setting = Settings.load();
        newGame();


    }

    /**
     * Main method.
     *
     * @param args arguments
     */
    public static void main(String[] args) {

        new Minesweeper();


    }

    public static int getPlayingSeconds() {
        int playingTime;
        playingTime = (int) ((System.currentTimeMillis() - startMillis) * 0.001);
        return playingTime;
    }

    public static Minesweeper getInstance(){
        return instance;
    }

    public static Settings getSetting() {
        return setting;
    }

    public static void setSetting(Settings setting) {
        setting.save();
    }

    public void newGame() {
        Field field = new Field(setting.getRowCount(), setting.getColumnCount(), setting.getMineCount());
        startMillis = System.currentTimeMillis();
        userInterface.newGameStarted(field);

    }
}
