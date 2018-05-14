package sk.tuke.gamestudio.Client;

import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.ScoreException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class RatingRestServiceClient implements RatingService {
    private static final String URL = "http://localhost:8080/gamestudio_war_exploded/api/rating";

    @Override
    public void addRating(Rating rating) throws ScoreException {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target(URL)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(rating, MediaType.APPLICATION_JSON), Response.class);
        } catch (Exception e) {
            throw new RuntimeException("Error saving score", e);
        }
    }

    @Override
    public String getAverageRatings(String game) {
        try {
            Client client = ClientBuilder.newClient();
            return client.target(URL)
                    .path("/" + game)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<String>() {
                    });
        } catch (Exception e) {
            throw new RuntimeException("Error loading score", e);
        }
    }

    @Override
    public List<Rating> getListOfRatings(String game) {
        return null;
    }

    @Override
    public void setRating(Rating rating) {

    }
}
