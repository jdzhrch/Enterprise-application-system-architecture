package chatroom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocketbot", decoders = { MessageDecoder.class }, encoders = { JoinMessageEncoder.class,
		ChatMessageEncoder.class, InfoMessageEncoder.class, UsersMessageEncoder.class })
public class BotEndpoint {
	private static final Logger logger = Logger.getLogger("BotEndpoint");
	@OnOpen
	public void openConnection(Session session) {
		logger.log(Level.INFO, "Connection opened.");
	}

	@OnMessage
	public void message(final Session session, Message msg) {
		logger.log(Level.INFO, "Received: {0}", msg.toString());
		if (msg instanceof JoinMessage) {
			JoinMessage jmsg = (JoinMessage) msg;
			session.getUserProperties().put("name", jmsg.getName());
			session.getUserProperties().put("active", true);
			logger.log(Level.INFO, "Received: {0}", jmsg.toString());
			sendAll(session, new InfoMessage(jmsg.getName() + " has joined the chat"));
			sendAll(session, new ChatMessage("Duke", jmsg.getName(), "Hi there!!"));
			sendAll(session, new UsersMessage(this.getUserList(session)));
		} else if (msg instanceof ChatMessage) {
			final ChatMessage cmsg = (ChatMessage) msg;
			logger.log(Level.INFO, "Received: {0}", cmsg.toString());
			sendAll(session, cmsg);
		}
	}

	@OnClose
	public void closedConnection(Session session) {
		session.getUserProperties().put("active", false);
		if (session.getUserProperties().containsKey("name")) {
			String name = session.getUserProperties().get("name").toString();
			sendAll(session, new InfoMessage(name + " has left the chat"));
			sendAll(session, new UsersMessage(this.getUserList(session)));
		}
		logger.log(Level.INFO, "Connection closed.");
	}

	@OnError
	public void error(Session session, Throwable t) {
		logger.log(Level.INFO, "Connection error ({0})", t.toString());
	}

	public synchronized void sendAll(Session session, Object msg) {
		try {
			for (Session s : session.getOpenSessions()) {
				if (s.isOpen()) {
					s.getBasicRemote().sendObject(msg);
					logger.log(Level.INFO, "Sent: {0}", msg.toString());
				}
			}
		} catch (IOException | EncodeException e) {
			logger.log(Level.INFO, e.toString());
		}
	}

	public List<String> getUserList(Session session) {
		List<String> users = new ArrayList<>();
		for (Session s : session.getOpenSessions()) {
			if (s.isOpen() && (boolean) s.getUserProperties().get("active"))
				users.add(s.getUserProperties().get("name").toString());
		}
		return users;
	}
}
