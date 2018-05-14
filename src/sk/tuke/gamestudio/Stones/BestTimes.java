package sk.tuke.gamestudio.Stones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;


public class BestTimes implements Iterable<BestTimes.PlayerTime> {

    private List<PlayerTime> playerTimes = new ArrayList<PlayerTime>();


    public Iterator<PlayerTime> iterator() {
        return playerTimes.iterator();
    }


    public void addPlayerTime(String name, int time) {
        this.playerTimes.add(new PlayerTime(name, time));
        Collections.sort(playerTimes);
    }


    @Override
    public String toString() {
        Formatter f = new Formatter();
        for(PlayerTime pt : this.playerTimes){
            f.format("%2d %4d (sec) %s",playerTimes.indexOf(pt)+1,pt.getTime(),pt.getName());
        }
        return f.toString();
    }

    public void reset() {
        this.playerTimes.clear();
    }

    public static class PlayerTime implements Comparable<PlayerTime> {

        private final String name;


        private final int time;


        public PlayerTime(String name, int time) {
            this.name = name;
            this.time = time;
        }


        public String getName() {
            return name;
        }


        public int getTime() {
            return time;
        }

        public int compareTo(PlayerTime o) {
            if (o.time > this.time) {
                return -1;
            } else if (o.time < this.time) {
                return 1;
            } else {
                return 0;
            }
        }


    }
}