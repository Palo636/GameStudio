package sk.tuke.gamestudio.Minesweeper;


import sk.tuke.gamestudio.Minesweeper.core.Field;

public interface UserInterface {
    void newGameStarted(Field field);

    void update();
}
