package sk.tuke.gamestudio.Minesweeper;

import java.io.*;

public class Settings implements Serializable {

    public static final Settings BEGINNER = new Settings(4, 4, 2);
    public static final Settings INTERMEDIATE = new Settings(5, 5, 1);
    public static final Settings EXPERT = new Settings(16, 30, 99);
    private static final String SETTING_FILE = System.getProperty("user.home") + System.getProperty("file.separator") + "minesweeper.settings";
    private final int rowCount;
    private final int columnCount;
    private final int mineCount;

    public Settings(int rowCount, int columnCount, int mineCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.mineCount = mineCount;
    }

    public static Settings load() {
        Settings setting = BEGINNER;
        try (FileInputStream is = new FileInputStream(SETTING_FILE);
             ObjectInputStream ois = new ObjectInputStream(is)) {
            setting = (Settings) ois.readObject();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            //System.err.println("subor sa nenasiel");
        }
        return setting = INTERMEDIATE;

    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getMineCount() {
        return mineCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Settings) {
            if ((((Settings) obj).getColumnCount() == this.getColumnCount())
                    && (((Settings) obj).getRowCount() == this.getRowCount())
                    && (((Settings) obj).getMineCount() == this.getMineCount())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return rowCount * columnCount * mineCount;
    }

    public void save() {
        try (FileOutputStream os = new FileOutputStream(SETTING_FILE);
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
