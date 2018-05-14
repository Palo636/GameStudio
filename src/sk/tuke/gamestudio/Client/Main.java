package sk.tuke.gamestudio.Client;

import sk.tuke.gamestudio.Minesweeper.Minesweeper;
import sk.tuke.gamestudio.Stones.Stones;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static int time = 0;


    public static void main(String[] args) throws ScoreException {
        ScoreRestServiceClient srsc = new ScoreRestServiceClient();
        CommentRestServiceClient crsc = new CommentRestServiceClient();
        RatingRestServiceClient rrsc = new RatingRestServiceClient();
        do {
            System.out.println("\n Zdravim ta v gamestudio, vyber si z moznosti: \n" +
                    "M-Minesweeper\n" +
                    "S-Stones\n" +
                    "X-Exit\n" +
                    "T-Tabulka\n" +
                    "C-Komenty\n" +
                    "R-Ratings");
            String input = readLine().toUpperCase();
            String game;
            switch (input) {
                case "R":
                    System.out.println("\n M-minesweeper rating\n S-stones rating");
                    input = readLine().toUpperCase();
                    switch (input) {
                        case "M":
                            System.out.println("hra minesweeper ma rating: " + rrsc.getAverageRatings("minesweeper"));
                            break;
                        case "S":
                            System.out.println("hra stones ma rating: " + rrsc.getAverageRatings("stones"));break;
                    }break;
                case "C":
                    List<Comment> listComment = null;
                    System.out.println("Zadaj nazov hry z ktorej chces zobrazit komenty:\"" +
                            "M or S");
                    input = readLine().toUpperCase();
                    if (input.equals("M")) {
                        game = "minesweeper";
                        listComment = crsc.getListOfCommentsByGame(game);
                    }
                    if (input.equals("S")) {
                        game = "stones";
                        listComment = crsc.getListOfCommentsByGame(game);
                    }
                    for (int i = 0; i < listComment.size(); i++) {
                        System.out.println(listComment.get(i).toString());
                    }
                    break;
                case "T":
                    List<Score> list;
                    System.out.println("chces to podla hry-G alebo playera-P?");
                    input = readLine();
                    if (input.toUpperCase().equals("G")) {
                        System.out.println("pre aku hru to chces minesweeper  or stones");
                        input = readLine();
                        list = srsc.getBestScoresForGame(input);
                    } else {
                        System.out.println("zadaj meno hraca");
                        input = readLine();
                        list = srsc.getScoresForPlayer(input);
                    }
                    System.out.println("--------------------------------------------");
                    System.out.println("TABULKA SCORE VYZERA:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(list.get(i));
                    }
                    System.out.println("--------------------------------------------");break;
                case "M":
                    game = "minesweeper";
                    new Minesweeper();
                    time = Minesweeper.getPlayingSeconds();
                    addCommentRating(srsc, crsc, rrsc, game);
                    break;
                case "S":
                    game = "stones";
                    new Stones();
                    addCommentRating(srsc, crsc, rrsc, game);
                    break;
                case "X":
                    System.out.println("ukoncuje sa g   amestudio");
                    System.exit(0);
                default:
                    System.out.println("!!!zly vstup, skus to znova!!!");
            }


        } while (true);
    }

    private static void addCommentRating(ScoreRestServiceClient srsc, CommentRestServiceClient crsc, RatingRestServiceClient rrsc, String game) throws ScoreException {
        String input;Score score = createScore(game, System.getProperty("user.name"), time);
        srsc.addScore(score);
        System.out.println("\nzadaj komentar k hre: ");
        input = readLine();
        Comment comment = new Comment(input, game, System.getProperty("user.name"));
        crsc.addComment(comment);
        System.out.println("\nzadaj rating lebo bude zle: ");
        int r = Integer.parseInt(readLine());
        Rating rating = new Rating(System.getProperty("user.name"), game, r);
        rrsc.addRating(rating);
    }

    static private String readLine() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    static private Score createScore(String game, String player, int time) {
        Date date = new Date();
        Score score = new Score();
        score.setGame(game);
        score.setPlayer(player);

        int points = 0;
        switch (game) {
            case "minesweeper":
                points = 5000 - time;
                break;
            case "stones":
                points = 2000 - time;
                break;
        }
        score.setPoints(points);
        score.setPlayedOn(date);
        return score;
    }

}
