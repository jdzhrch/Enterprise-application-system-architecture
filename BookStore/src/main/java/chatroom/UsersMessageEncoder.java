package chatroom;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class UsersMessageEncoder implements Encoder.Text<UsersMessage> {
	@Override
	public void init(EndpointConfig ec) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(UsersMessage usersMessage) throws EncodeException {
		StringWriter swriter = new StringWriter();
		try (JsonGenerator jsonGen = Json.createGenerator(swriter)) {
			jsonGen.writeStartObject().write("type", "users").writeStartArray("userlist");
			for (String user : usersMessage.getUserlist())
				jsonGen.write(user);
			jsonGen.writeEnd().writeEnd();
		}
		return swriter.toString();
	}
}