/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package base;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Jesse
 */
@Entity
public class Twat implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
    
    String title;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
    
    String contents;

    public String getContents()
    {
        return contents;
    }

    public void setContents(String contents)
    {
        this.contents = contents;
    }
    
    @OneToMany
    List<Accunt> hearts;

    public List<Accunt> getHearts()
    {
        return hearts;
    }

    public void setHearts(List<Accunt> hearts)
    {
        this.hearts = hearts;
    }
    
    @OneToMany
    List<Accunt> retwats;

    public List<Accunt> getRetwats()
    {
        return retwats;
    }

    public void setRetwats(List<Accunt> retwats)
    {
        this.retwats = retwats;
    }
    
    @OneToMany
    List<Accunt> mentions;

    public List<Accunt> getMentions()
    {
        return mentions;
    }

    public void setMentions(List<Accunt> mentions)
    {
        this.mentions = mentions;
    }
    
}
