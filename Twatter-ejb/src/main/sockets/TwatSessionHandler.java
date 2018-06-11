/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.Session;

/**
 *
 * @author Jesse
 */
public class TwatSessionHandler
{
    private List<Session> sessions = new ArrayList<>();
    private static final Logger LOG = Logger.getLogger(TwatSessionHandler.class.getName());
    
    private TwatSessionHandler()
    {
    }

    private static TwatSessionHandler instance;

    public static TwatSessionHandler getInstance()
    {
        if (instance == null)
        {
            instance = new TwatSessionHandler();
        }
        return instance;
    }

    public void Connect(Session session)
    {
        sessions.add(session);
    }

    public void Disconnect(Session session)
    {
        sessions.remove(session);
    }
    
    
    public void handleMessage(String message, Session session)
    {
        try (JsonReader reader = Json.createReader(new StringReader(message)))
        {
            JsonObject jsonMessage = reader.readObject();

            // Send to everyone connected
            this.Send(jsonMessage, session);
        }
        catch (Exception ex)
        {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void Send(JsonObject message, Session from)
    {
        for (Session s : sessions)
        {
            SendTo(message, s);
        }
    }

    public void SendTo(JsonObject message, Session to)
    {
        try
        {
            to.getBasicRemote().sendText(message.toString());
        }
        catch (IOException ex)
        {
            Disconnect(to);
            Logger.getLogger(TwatSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
