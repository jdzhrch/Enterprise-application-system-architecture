package chatroom;

public class JoinMessage extends Message {
	private String name;

	public JoinMessage(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}