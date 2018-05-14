package sk.tuke.gamestudio.Stones;

public class Stones {
    private static Stones instance;
    private static long startMillis;
    private Console console;
    private BestTimes bestTimes = new BestTimes();
    private Field field;


    public Stones() {
        instance = this;
        startMillis = System.currentTimeMillis();
        field = Field.Load();
        console = new Console();
        console.newGameStarted(field);
    }

    public static Stones getInstance() {
        return instance;
    }

    public static void main(String[] args) throws Exception {
        new Stones();
    }

    public int getPlayingSeconds() {
        int playingTime;
        playingTime = (int) ((System.currentTimeMillis() - startMillis) * 0.001);
        return playingTime;
    }

    public BestTimes getBestTimes() {
        return bestTimes;
    }

//    public Field getField(){
//        return field;
//    }
//
//    public void setField(Field field){
//        this.field = field;
//        field.save();
//    }


}
