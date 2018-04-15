package chatroom;

public class ChatMessage extends Message {
	private String name;
	private String target;
	private String message;

	public ChatMessage(String name, String target, String message) {
		this.setName(name);
		this.setTarget(target);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}