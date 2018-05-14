/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonen;

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
        TwatterAccount cunt1 = new TwatterAccount("Deij1997");
        TwatterAccount cunt2 = new TwatterAccount("RandomUsername");

        cunt1.follow(cunt2);
        cunt2.follow(cunt1);

        try
        {
            cunt1.twat("derp", "Deze site is echt gay");
            Thread.sleep(2);
            cunt2.twat("hallo", "dit is een twat lel");
        }
        catch (Exception e)
        {
        }

        em.persist(cunt1);
        em.persist(cunt2);
    }
}
