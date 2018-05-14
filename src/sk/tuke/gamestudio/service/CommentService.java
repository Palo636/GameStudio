package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;

import java.util.List;

public interface CommentService {
    void addComment(Comment comment) throws ScoreException;
    List<Comment> getListOfCommentsByGame(String game) throws ScoreException;
    Comment getcomment(Integer id) throws ScoreException;
}
