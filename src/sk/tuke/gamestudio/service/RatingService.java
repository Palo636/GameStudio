package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;

import java.util.List;

public interface RatingService {
    void addRating(Rating rating) throws ScoreException;
    String getAverageRatings(String game);
    List<Rating> getListOfRatings(String game);
    void setRating(Rating rating);
}
