package sk.tuke.gamestudio.Minesweeper;

import java.sql.Connection;

public class DatabaseSetting {
    public static final String URL = "jdbc:postgresql://localhost/minesweeper";
    public static final String USER = "postgres";
    public static final String PASSWORD = "54485285";

    public static final String QUERY_CREATE_BEST_TIMES = "CREATE TABLE player_time (name VARCHAR(128) NOT NULL, best_time INT NOT NULL)";
    public static final String QUERY_ADD_BEST_TIME = "INSERT INTO player_time (name, best_time) VALUES (?, ?)";
    public static final String QUERY_SELECT_BEST_TIMES = "SELECT name, best_time FROM player_time";
    public DatabaseSetting() {

    }


}