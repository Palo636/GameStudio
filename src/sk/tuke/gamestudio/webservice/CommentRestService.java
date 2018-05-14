package sk.tuke.gamestudio.webservice;

import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.ScoreException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/comment")
public class CommentRestService {

    @EJB
    CommentService commentService;

    @POST
    public Comment addComment(Comment comment) throws ScoreException {
        commentService.addComment(comment);
        return comment;
    }

    @GET
    @Path("/{game}")
    @Produces("application/json")
    public List<Comment> getListOfCommentsByGame(@PathParam("game") String game) throws ScoreException {
        return commentService.getListOfCommentsByGame(game);
    }

    @GET
    @Path("/id{id}")
    @Produces("application/json")
    public Response getComment(@PathParam("id") Integer id) throws ScoreException {
        Comment comment = commentService.getcomment(id);
        if (comment == null) {
            return Response.status(404).build();
        } else {
            return Response.ok(comment).build();
        }
    }
}
