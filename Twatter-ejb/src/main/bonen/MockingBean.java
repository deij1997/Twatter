/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonen;

import base.TwatterAccount;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jesse
 */
@Singleton
@Startup
public class MockingBean
{

    @PersistenceContext(name = "Twatter")
    EntityManager em;

    @PostConstruct
    public void init()
    {
        TwatterAccount acc1 = new TwatterAccount("Deij1997");
        TwatterAccount acc2 = new TwatterAccount("RandomUsername");
        TwatterAccount acc3 = new TwatterAccount("Blepper");
        TwatterAccount acc4 = new TwatterAccount("Christensen");

        acc1.follow(acc2);
        acc1.follow(acc3);
        acc1.follow(acc4);
        acc2.follow(acc1);
        acc2.follow(acc3);
        acc2.follow(acc4);
        acc3.follow(acc1);
        acc3.follow(acc2);
        acc3.follow(acc4);
        acc4.follow(acc1);
        acc4.follow(acc2);
        acc4.follow(acc3);

        try
        {
            acc1.twat("derp", "Deze site is echt bagger");
            Thread.sleep(2);
            acc2.twat("hallo", "dit is een twat lel");
            Thread.sleep(2);
            acc3.twat("heeeeey", "ik ga jullie met notificaties spammen");
            Thread.sleep(2);
            acc1.twat("pws no", "pws doe dat niet man ik word al gek genoeg van fking whatsapp groepen");
            Thread.sleep(2);
            acc4.twat("help", "i dont want to be here please someone get me out of here!!");
        }
        catch (Exception e)
        {
        }

        em.persist(acc1);
        em.persist(acc2);
        em.persist(acc3);
        em.persist(acc4);
    }
}
