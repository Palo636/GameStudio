package sk.tuke.gamestudio.TicTacToe;

public class Field {

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public Tile getTiles(int row, int column) {
        return tiles[row][column];
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    private final int rowCount;

    private final int columnCount;

    private Tile[][] tiles;

    public Field(int rowCount, int columnCount){
        this.rowCount=rowCount;
        this.columnCount=columnCount;
        tiles = new Tile[rowCount][columnCount];
        generate();
    }

    public void generate(){
        for (int i = 0; i<rowCount; i++){
            for(int j = 0; j < columnCount; j++){
                tiles[i][j] = new Tile();
            }
        }
    }


}
