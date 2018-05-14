package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Score;

import java.util.List;

public interface ScoreService {
    void addScore(Score score) throws ScoreException;
    List<Score> getBestScoresForGame(String game) throws ScoreException;
    List<Score> getScoresForPlayer(String player) throws ScoreException;
//    Score getScore(Integer id) throws ScoreException;
//    Score updateScore(Score score) throws ScoreException;
//    void delete(Integer id) throws ScoreException;
}
