/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bonen;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jesse
 */
@Stateless
@Path("/auth")
public class AuthBean
{
    @GET
    @Path(value = "/generate")
    public Response GetSecurity()
    {
        // maak een header aan (moet door of frontend of backend worden opgeslagen)
        return Response.ok().header(HttpHeaders.AUTHORIZATION, "eenofanderestring").build();
    }
    
    @GET
    @Path(value = "/validate")
    public void HasSecurity(@HeaderParam(HttpHeaders.AUTHORIZATION)String authorization)
    {
        // check of het request een header heeft
        if (authorization == null)
        {
            throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED));
        }
    }
    
    @GET
    @Path(value = "/invalidate")
    public Response LoseSecurity()
    {
        // zet de header naar null (moet door of de frontend of de backend naar null worden gezet)
        return Response.ok().header(HttpHeaders.AUTHORIZATION, null).build();
    }
}
