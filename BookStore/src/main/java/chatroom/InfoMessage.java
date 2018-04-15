package chatroom;

public class InfoMessage extends Message {
	private String info;

	public void setInfo(String info) {
		this.info = info;
	}

	public InfoMessage(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	/* For logging purposes */
	@Override
	public String toString() {
		return "[InfoMessage] " + info;
	}
}