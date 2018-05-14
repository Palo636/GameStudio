package sk.tuke.gamestudio.Stones;

import java.io.*;
import java.util.Random;

public class Field implements Serializable {

    private final int rowCount;

    private final int columnCount;
    int holeRow;
    int holeColumn;
    private Stone[][] stones;
    private GameState state = GameState.PLAYING;

    public Field(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        stones = new Stone[rowCount][columnCount];
        generate();

    }

    public void moveStone(Direction direction) {
        int hlpValue;try{
        switch (direction) {
            case UP:
                hlpValue = getStone(holeRow+1, holeColumn).getValue();
                getStone(holeRow+1, holeColumn).setValue(0);
                getStone(holeRow, holeColumn).setValue(hlpValue);
                holeRow+=1;
                break;
            case DOWN:
                hlpValue = getStone(holeRow-1, holeColumn).getValue();
                getStone(holeRow-1, holeColumn).setValue(0);
                getStone(holeRow, holeColumn).setValue(hlpValue);
                holeRow-=1;
                break;
            case RIGHT:
                hlpValue = getStone(holeRow, holeColumn-1).getValue();
                getStone(holeRow, holeColumn-1).setValue(0);
                getStone(holeRow, holeColumn).setValue(hlpValue);
                holeColumn-=1;
                break;
            case LEFT:
                hlpValue = getStone(holeRow, holeColumn+1).getValue();
                getStone(holeRow, holeColumn+1).setValue(0);
                getStone(holeRow, holeColumn).setValue(hlpValue);
                holeColumn+=1;
                break;
        }}catch (ArrayIndexOutOfBoundsException e){
            System.out.println("zly krok");
            return;
        }
        if(isSolved()) {
            state = GameState.SOLVED;
        }
    }

    public void generate() {
        Random random = new Random();
        int column, row;
        int stone = columnCount * rowCount;
        for (int i = 0; i < stone; i++) {
            column = random.nextInt(getColumnCount());
            row = random.nextInt(getRowCount());
            if (stones[row][column] == null) {
                stones[row][column] = new Stone(i);
                if(stones[row][column].getValue()==0){
                    holeRow = row;
                    holeColumn = column;
                }
            } else {
                i--;
            }
        }


    }

    public boolean isSolved(){
        int compareValue = 0;
        for(int i = 0; i < columnCount; i++){
            for(int j = 0; j < rowCount; j++){
                compareValue++;
                if(compareValue==columnCount*rowCount){
                    compareValue=0;
                }
                if(compareValue == getStone(i, j).getValue()){

                }else{
                    return false;
                }
            }
        }
        return true;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;

    }

    public Stone getStone(int row, int column) {
        return stones[row][column];
    }

    private static final String SETTING_FILE = "game.settings";

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

    public static Field Load() {
        Field field = new Field(5, 5);

        try {
            FileInputStream in = new FileInputStream(SETTING_FILE);
            ObjectInputStream input = new ObjectInputStream(in);
            field = (Field) input.readObject();


        } catch (ClassNotFoundException po) {
            System.err.printf("Subor sa nenasiel\n");
        } catch (IOException ex) {
            ex.getMessage();
        }
        return field;
    }


}
