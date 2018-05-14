package sk.tuke.gamestudio.entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Comment.getCommentsByGame",
        query = "SELECT s FROM Comment s WHERE s.game=:game")
public class Comment {
    private Integer id;
    private String comment;
    private String game;
    private String player;

    public Comment() {
    }

    public Comment(Integer id, String comment, String game, String player) {
        this.id = id;
        this.comment = comment;
        this.game = game;
        this.player = player;
    }

    public Comment(String comment, String game, String player) {
        this.comment = comment;
        this.game = game;
        this.player = player;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", player='" + player + '\'' +
                ", game='" + game + '\'' +
                ", comment=" + comment +
                '}';
    }
}
