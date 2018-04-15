package chatroom;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
public class InfoMessageEncoder implements Encoder.Text<InfoMessage> {
	@Override
	public void init(EndpointConfig ec) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(InfoMessage joinMessage) throws EncodeException {
		StringWriter swriter = new StringWriter();
		try (JsonGenerator jsonGen = Json.createGenerator(swriter)) {
			jsonGen.writeStartObject().write("type", "info").write("info", joinMessage.getInfo()).writeEnd();
		}
		return swriter.toString();
	}
}