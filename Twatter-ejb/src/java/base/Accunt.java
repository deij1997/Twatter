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
public class Accunt implements Serializable
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
    
    private String username;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
    
    private String bio;

    public String getBio()
    {
        return bio;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }
    
    private String location;

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }
    
    private String website;

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }
    
    @OneToMany
    private List<Accunt> followers;

    public List<Accunt> getFollowers()
    {
        return followers;
    }

    public void setFollowers(List<Accunt> followers)
    {
        this.followers = followers;
    }
    
    @OneToMany
    private List<Accunt> following;

    public List<Accunt> getFollowing()
    {
        return following;
    }

    public void setFollowing(List<Accunt> following)
    {
        this.following = following;
    }
    
    
}
