/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Jesse
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Twat.findByContent",
                query="SELECT t FROM Twat t WHERE t.contents LIKE :contents"),
})
public class Twat implements Serializable, Comparable<Twat>
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String title;
    String contents;
    @OneToMany
    List<Accunt> retwats;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date senddate;
    @ManyToOne
    private Accunt poster;

    @Deprecated
    public Twat()
    {
    }

    public Twat(String title, String contents, Accunt postman)
    {
        this.title = title;
        this.contents = contents;
        this.poster = postman;
        
        this.retwats = new ArrayList<>();
        
        this.senddate = new Date();
    }
    
    public Accunt getPoster()
    {
        return poster;
    }

    public void setPoster(Accunt poster)
    {
        this.poster = poster;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContents()
    {
        return contents;
    }

    public void setContents(String contents)
    {
        this.contents = contents;
    }

    public List<Accunt> getRetwats()
    {
        return retwats;
    }

    public void setRetwats(List<Accunt> retwats)
    {
        this.retwats = retwats;
    }

    public Date getSenddate()
    {
        return senddate;
    }

    public void setSenddate(Date senddate)
    {
        this.senddate = senddate;
    }

    @Override
    public int compareTo(Twat o)
    {
        return o.senddate.compareTo(senddate);
    }
}
