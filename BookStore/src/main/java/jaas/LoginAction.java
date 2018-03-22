package jaas;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import action.BaseAction;
import jaas.SimpleCallbackHandler;

import service.AppService;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private AppService appService;
	private String username;
	private String password;

	public AppService getLoginservice() {
		return appService;
	}

	public void setLoginservice(AppService appService) {
		this.appService = appService;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String login() {

		System.out.println(username);
		try {
			System.setProperty("java.security.auth.login.config",
					"E:/GitHub/proj/Enterprise application system architecture/BookStore/src/main/java/jaas/jaas.conf");

			LoginContext context = new LoginContext("Login1",
					new SimpleCallbackHandler(username, password.toCharArray()));
			context.login();
			Subject subject = context.getSubject();
			SimplePrincipal simplePrincipal = new SimplePrincipal("role", "admin");
			if (subject.getPrincipals().contains(simplePrincipal)) {
				context.logout();
				request().setAttribute("errorMsg", "Login success");
				return "login success";
			}
			context.logout();
			request().setAttribute("errorMsg", "Login Error:this user does not exist");
			return "login fail";

		}

		catch (LoginException e) {
			e.printStackTrace();
			return "login error";
		} catch (SecurityException e) {
			e.printStackTrace();
			return "login error";
		}
	}

}