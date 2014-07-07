package library;


import java.sql.ResultSet;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

public class Visitor {
	protected HttpSession session;
	public Visitor(HttpSession session) throws IllegalArgumentException {
		if(session == null) {
			throw new IllegalArgumentException("session can't be null");
		}
		this.session = session;
	}
	public boolean tryLogin(String username, String password) {
		if(username == null || password == null) {
			return false;
		}
		username = username.toLowerCase();
		Admin admin = Admin.getByUsername(username);
		if(admin == null || !BCrypt.checkpw(password, admin.getPassHash())) {
			return false;
		} else {
			session.setAttribute("logged_in", true);
			session.setAttribute("admin_id", admin.getId());
			return true;
		}
	}
	public boolean isLoggedIn() {
		Object loggedIn = session.getAttribute("logged_in");
		return loggedIn != null && (Boolean)loggedIn;
	}
}
