package chatroom;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {
	@Override
	public void init(EndpointConfig ec) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(ChatMessage chatMessage) throws EncodeException {
		StringWriter swriter = new StringWriter();
		try (JsonGenerator jsonGen = Json.createGenerator(swriter)) {
			jsonGen.writeStartObject().write("type", "chat").write("name", chatMessage.getName())
					.write("target", chatMessage.getTarget()).write("message", chatMessage.getMessage()).writeEnd();
		}
		return swriter.toString();
	}
}