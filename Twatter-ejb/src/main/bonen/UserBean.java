/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonen;

import base.TwatterAccount;
import java.util.List;
import javax.ejb.Stateless;
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
@Path("/users")
public class UserBean
{
    @PersistenceContext(name = "Twatter")
    private EntityManager em;

    @POST
    @Produces(
            {
                MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
            })
    @Path("{username}/update/details")
    public Response UploadDetails(@PathParam("username") String username, @FormParam("bio") String bio)
    {
        int length = bio.length();

        if (length > 160)
        {
            return Response.status(501).entity("bio exceeded a length of 160 symbols!").build();
        }
        else
        {
            GetUser(username).setBio(bio);

            return Response.ok().build();
        }
    }

    @POST
    @Produces(
            {
                MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
            })
    @Path("{username}/update/username")
    public Response ChangeUsername(@PathParam("username") String username, @FormParam("newusername") String newusername)
    {
        if (GetUser(newusername) != null)
        {
            return Response.status(501).entity("username already in use").build();
        }
        
        GetUser(username).setUsername(newusername);
        
        return Response.ok().build();
    }

    @GET
    @Produces(
            {
                MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
            })
    @Path("{username}")
    public TwatterAccount GetUser(@PathParam("username") String username)
    {
        TypedQuery<TwatterAccount> query = em.createNamedQuery("TwatterAccount.findByUsername", TwatterAccount.class);
        query.setParameter("username", username);

        List<TwatterAccount> cunts =  query.getResultList();
        if (cunts.isEmpty()) {
            return null;
        }
        return cunts.get(0);
    }

    @GET
    @Produces(
            {
                MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
            })
    @Path("{username}/followers")
    public List<TwatterAccount> GetFollowers(@PathParam("username") String username)
    {
        return GetUser(username).getFollowers();
    }

    @GET
    @Produces(
            {
                MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
            })
    @Path("{username}/following")
    public List<TwatterAccount> GetFollowing(@PathParam("username") String username)
    {
        return GetUser(username).getFollowing();
    }

    @POST
    @Produces(
            {
                MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
            })
    @Path("{username}/follow")
    public void FollowUser(@PathParam("username") String username, @FormParam("otheruser") String otheruser)
    {
        GetUser(username).follow(GetUser(otheruser));
    }

    @POST
    @Produces(
            {
                MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON
            })
    @Path("{username}/unfollow")
    public void UnFollowUser(@PathParam("username") String username, @FormParam("otheruser") String otheruser)
    {
        GetUser(username).unfollow(GetUser(otheruser));
    }
}
