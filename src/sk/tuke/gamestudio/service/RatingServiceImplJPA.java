package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RatingServiceImplJPA implements RatingService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRating(Rating rating){
        try {
            Rating or = (Rating) entityManager.createQuery("SELECT r FROM Rating r WHERE r.game=:game AND r.player=:player").setParameter("game", rating.getGame())
                    .setParameter("player", rating.getPlayer()).getSingleResult();
            or.setRating(rating.getRating());
        } catch (NoResultException e) {
            entityManager.persist(rating);
        }
    }

    @Override
    public String getAverageRatings(String game) {
        System.out.println(entityManager.createNamedQuery("Rating.averageRating").setParameter("game", game).getSingleResult());
        String rating = entityManager.createNamedQuery("Rating.averageRating").setParameter("game", game).getSingleResult().toString();
        return rating == null ? "0" : rating;
    }


    @Override
    public List<Rating> getListOfRatings(String game) {
        return entityManager.createNamedQuery("Rating.listOfRatings").setParameter("game", game).getResultList();
    }

    @Override
    public void setRating(Rating rating) {
        Rating r = (Rating) entityManager.createNamedQuery("updateRating").setParameter("player", rating.getPlayer())
                .setParameter("game", rating.getGame()).getSingleResult();
        if (r.getGame() == rating.getGame() && r.getPlayer() == rating.getPlayer()) {
            r.setRating(rating.getRating());
            entityManager.merge(r);
        } else {
            entityManager.persist(rating);

        }
    }
}
