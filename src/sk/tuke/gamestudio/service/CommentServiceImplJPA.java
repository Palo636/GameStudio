package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CommentServiceImplJPA implements CommentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addComment(Comment comment) throws ScoreException {
        entityManager.persist(comment);
    }

    @Override
    public List<Comment> getListOfCommentsByGame(String game) throws ScoreException {
        return entityManager.createNamedQuery("Comment.getCommentsByGame").setParameter("game", game).getResultList();
    }

    @Override
    public Comment getcomment(Integer id) throws ScoreException {
        return entityManager.find(Comment.class, 1);
    }
}
