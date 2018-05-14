package sk.tuke.gamestudio.Client;

import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.ScoreException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class CommentRestServiceClient implements CommentService {
    private static final String URL = "http://localhost:8080/gamestudio_war_exploded/api/comment";

    @Override
    public void addComment(Comment comment)  {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target(URL)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(comment, MediaType.APPLICATION_JSON), Response.class);
        } catch (Exception e) {
            throw new RuntimeException("Error saving score", e);
        }
    }

    @Override
    public List<Comment> getListOfCommentsByGame(String game) throws ScoreException {
        try {
            Client client = ClientBuilder.newClient();
            return client.target(URL)
                    .path("/" + game)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Comment>>() {
                    });
        } catch (Exception e) {
            throw new RuntimeException("Error loading score", e);
        }
    }

    @Override
    public Comment getcomment(Integer id) throws ScoreException {
        return null;
    }

}
