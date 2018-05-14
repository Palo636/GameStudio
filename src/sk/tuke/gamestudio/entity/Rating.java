package sk.tuke.gamestudio.entity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "Rating.averageRating",
                query = "SELECT AVG(s.rating) FROM Rating s WHERE s.game=:game"),
        @NamedQuery(name = "Rating.listOfRatings",
                query = "SELECT s FROM Rating s WHERE s.game=:game"),
        @NamedQuery(name = "updateRating",
                query ="SELECT s FROM Rating  s WHERE s.player=:player AND s.game=:game")})

@Entity
public class Rating {
    @Id
    @GeneratedValue
    private Integer id;
    private String player;
    private String game;
    private int rating;

    public Rating() {
    }

    public Rating(String player, String game, int rating) {
        this.player = player;
        this.game = game;
        this.rating = rating;
    }

    public Rating(Integer id, String player, String game, int rating) {
        this.id = id;
        this.player = player;
        this.game = game;
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    //@Id
    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
