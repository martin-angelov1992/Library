package library;
import javax.persistence.Entity;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;


public class AdminVisitor extends Visitor {
	private Admin admin;
	public AdminVisitor(HttpSession session, Admin admin) throws IllegalArgumentException {
		super(session);
		this.admin = admin;
	}
	public String getUsername() {
		return admin.getUsername();
	}
	public void logout() {
		session.removeAttribute("logged_in");
		session.removeAttribute("admin_id");
	}
}
