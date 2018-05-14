package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Score;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ScoreServiceImplJPA implements ScoreService {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private JMSContext context;

    @Resource(lookup = "jms/achievementQueue")
    private Queue queue;

    @Override
    public void addScore(Score score) throws ScoreException {
        entityManager.persist(score);
        context.createProducer().send(queue, context.createTextMessage("new great score for player" + score.getPlayer()));
    }

    @Override
    public List<Score> getBestScoresForGame(String game) throws ScoreException {
        return entityManager.createNamedQuery("Score.getBestScoresForGame")
                .setParameter("game", game).setMaxResults(10).getResultList();
    }

    @Override
    public List<Score> getScoresForPlayer(String player) throws ScoreException{
        return entityManager.createNamedQuery("Score.getScoresForPlayer")
                .setParameter("player", player).setMaxResults(10).getResultList();
    }


//    public Score getScore(Integer id) {
//        return entityManager.find(Score.class, id);
//    }
//
//    public Score updateScore(Score score){
//        return entityManager.merge(score);
//
//    }

    public void delete(Integer id) {
        Score scoree =  entityManager.find(Score.class, id);
        entityManager.remove(scoree);

    }
}
