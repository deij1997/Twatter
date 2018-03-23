/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import base.Accunt;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author Jesse
 */
@Singleton
@Path("/test")
public class MockingBean
{
    
    @PersistenceContext(name = "Twatter")
    EntityManager em;
    
    @POST
    @Path("/init")
    public void init()
    {
        Accunt cunt1 = new Accunt("cunt1");
        Accunt cunt2 = new Accunt("cunt2");
        
        cunt1.follow(cunt2);
        cunt2.follow(cunt1);
        cunt1.twat("1titel", "appelsap");
       
        em.persist(cunt1);
        em.persist(cunt2);
    }
}
