/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import base.TwatterAccount;
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
        TwatterAccount cunt1 = new TwatterAccount("cunt1");
        TwatterAccount cunt2 = new TwatterAccount("cunt2");
        
        cunt1.follow(cunt2);
        cunt2.follow(cunt1);
        cunt1.twat("1titel", "appelsap");
       
        em.persist(cunt1);
        em.persist(cunt2);
    }
}
