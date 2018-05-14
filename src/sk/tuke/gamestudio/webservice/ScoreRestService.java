package sk.tuke.gamestudio.webservice;


import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreException;
import sk.tuke.gamestudio.service.ScoreService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/score")
public class ScoreRestService {
    @EJB
    private ScoreService scoreService;

    @POST
   // @Path("addScore")
    @Consumes("application/json")
    public Score addScore(Score score) throws ScoreException {
        System.out.println(score.toString());
        scoreService.addScore(score);
        return score;
    }

    @GET
    @Path("/{game}")
    @Produces("application/json")
    public List<Score> getBestScoresForGame(@PathParam("game") String game) throws ScoreException {
        return scoreService.getBestScoresForGame(game);
    }

    @GET
    @Path("/player/{player}")
    @Produces("application/json")
    public List<Score> getScoresForPlayer(@PathParam("player") String player) throws ScoreException {
        return scoreService.getScoresForPlayer(player);
    }

//    @GET
//    @Path("/id{id}")
//    @Produces("application/json")
//    public Response getScoreByID(@PathParam("id") Integer id) throws ScoreException {
//        Score score = scoreService.getScore(id);
//        if (score == null) {
//            return Response.status(404).build();
//        } else {
//            return Response.ok(score).build();
//        }
//    }

//    @PUT
//    @Path("/id/{id}")
//    @Produces("application/json")
//    @Consumes("application/json")
//    public Score updateScoreByID(Score score) throws ScoreException {
//        return scoreService.updateScore(score);
//    }


//    @DELETE
//    @Path("id/{id}")
//    public Response delete(@PathParam("id") Integer id ) throws ScoreException {
//        scoreService.delete(id);
//        return Response.ok().build();
//    }


}

