/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.File;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jesse
 */

@Stateless
@Named
@Path("/UserBean")
public class UserBean
{
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{username}/photo/upload")
    public boolean UploadImage(@PathParam("username") String username, @FormParam("image") File file)
    {
        //todo check if the attached file is an image
        return false;
    }
    
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{username}/profile/details")
    public boolean UploadDetails(@PathParam("username") String username, @FormParam("bio") String bio, @FormParam("location") String location, @FormParam("website") String website)
    {
        //todo check if bio is equal to or less than 160 characters
        return false;
    }
    
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{username}/profile/username")
    public boolean ChangeUsername(@PathParam("username") String username, @FormParam("newusername") String newusername)
    {
        //todo change the old username into the new username
        return false;
    }
}
