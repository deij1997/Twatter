/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jesse
 */
@Entity
@NamedQueries(
        {
            @NamedQuery(name = "TwatterAccount.findByUsername",
                        query = "SELECT a FROM TwatterAccount a WHERE a.username = :username"),
        })
public class TwatterAccount implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    private String bio;
    
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "follower_user",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private List<TwatterAccount> followers;
    
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "follow_user",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "follow_id")
    )
    private List<TwatterAccount> following;
    
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "twat_user",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "twat_id")
    )
    private List<Twat> twats;

    @Deprecated
    public TwatterAccount()
    {
        this.twats = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public TwatterAccount(String username)
    {
        this();
        this.username = username;
    }

    @XmlTransient
    public List<Twat> getTwats()
    {
        return twats;
    }

    public void setTwats(List<Twat> twats)
    {
        this.twats = twats;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getBio()
    {
        return bio;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }

    @XmlTransient
    public List<TwatterAccount> getFollowers()
    {
        return followers;
    }

    public void setFollowers(List<TwatterAccount> followers)
    {
        this.followers = followers;
    }

    @XmlTransient
    public List<TwatterAccount> getFollowing()
    {
        return following;
    }

    public void setFollowing(List<TwatterAccount> following)
    {
        this.following = following;
    }

    public void twat(Twat newtwat)
    {
        twats.add(newtwat);
    }

    public void follow(TwatterAccount who)
    {
        this.following.add(who);

        who.followers.add(this);
    }

    public void unfollow(TwatterAccount who)
    {
        this.following.remove(who);

        who.followers.remove(this);
    }

    public void twat(String titel, String contents)
    {
        Twat t = new Twat(titel, contents, this);
        twat(t);
    }
}
