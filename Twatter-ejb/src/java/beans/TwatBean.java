/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import base.Accunt;
import base.Twat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jesse
 */
@Stateless
@Named
@Path("/twats")
public class TwatBean
{
    @Inject
    private UserBean user;

    @PersistenceContext(name = "Twatter")
    private EntityManager em;

    @POST
    @Produces(
            {
                MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
            })
    @Path("{username}/twat")
    public Response PostTwat(@PathParam("username") String username, @FormParam("title") String title, @FormParam("contents") String contents)
    {
        Accunt cunt = user.GetUser(username);

        if (cunt == null)
        {
            return Response.status(501).build();
        }

        Twat newtwat = new Twat(title, contents, cunt);

        cunt.twat(newtwat);

        return Response.ok(newtwat).build();
    }

    @POST
    @Produces(
            {
                MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
            })
    @Path("{username}/retwat")
    public Response ReTwat(@PathParam("username") String username, @FormParam("originaltwat") String originaltwat)
    {
        GetTwat(originaltwat).getRetwats().add(user.GetUser(username));

        return Response.ok().build();
    }

    @POST
    @Produces(
            {
                MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
            })
    @Path("/search")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public List<Twat> SearchTwat(@FormParam("contents") String contents)
    {
        TypedQuery<Twat> query = em.createNamedQuery("Twat.findByContent", Twat.class);
        query.setParameter("contents", "%" + contents + "%");
        List<Twat> results = query.getResultList();

        return results;
    }

    @GET
    @Produces(
            {
                MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
            })
    @Path("{username}/timeline")
    public List<Twat> GetTwatLine(@PathParam("username") String username)
    {
        //todo something to retweet the twat
        Accunt me = user.GetUser(username);
        List<Accunt> cunts = me.getFollowing();
        List<Twat> allTwats = new ArrayList<>();

        for (Accunt cunt : cunts)
        {
            allTwats.addAll(cunt.getTwats());
        }
        
        allTwats.addAll(me.getTwats());

        Collections.sort(allTwats);

        return allTwats;
    }

    @GET
    @Produces(
            {
                MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
            })
    @Path("/{id}")
    public Twat GetTwat(@PathParam("id") String id)
    {
        return em.find(Twat.class, Long.parseLong(id));
    }
}
