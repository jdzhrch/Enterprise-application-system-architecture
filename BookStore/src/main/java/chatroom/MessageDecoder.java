package chatroom;

import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message> {
	private Map<String, String> messageMap;

	@Override
	public void init(EndpointConfig ec) {
	}

	@Override
	public void destroy() {
	}

	/* Create a new Message object if the message can be decoded */
	@Override
	public Message decode(String string) throws DecodeException {
		Message msg = null;
		if (willDecode(string)) {
			switch (messageMap.get("type")) {
			case "join":
				msg = new JoinMessage(messageMap.get("name"));
				break;
			case "chat":
				msg = new ChatMessage(messageMap.get("name"), messageMap.get("target"), messageMap.get("message"));
			}
		} else {
			throw new DecodeException(string, "[Message] Can't decode.");
		}
		return msg;
	}

	@Override
	public boolean willDecode(String string) {
		boolean decodes = false;
		messageMap = new HashMap<>();
		JsonParser parser = Json.createParser(new StringReader(string));
		while (parser.hasNext()) {
			if (parser.next() == JsonParser.Event.KEY_NAME) {
				String key = parser.getString();
				parser.next();
				String value = parser.getString();
				messageMap.put(key, value);
			}
		}
		Set keys = messageMap.keySet();
		if (keys.contains("type")) {
			switch (messageMap.get("type")) {
			case "join":
				if (keys.contains("name"))
					decodes = true;
				break;
			case "chat":
				String[] chatMsgKeys = { "name", "target", "message" };
				if (keys.containsAll(Arrays.asList(chatMsgKeys)))
					decodes = true;
				break;
			}
		}
		return decodes;
	}
}