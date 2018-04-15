package chatroom;

import java.util.List;

public class UsersMessage extends Message {
	private List<String> userlist;

	public UsersMessage(List<String> userlist) {
		this.setUserlist(userlist);
	}

	public List<String> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<String> userlist) {
		this.userlist = userlist;
	}

}