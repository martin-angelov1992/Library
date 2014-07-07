package library;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;


public class Global {
	public static EntityManager em; // Used for DB access
	public static Visitor visitor;
	public static final String urlPrefix = "WebTest2/";
	
	public static void handleGlobals(HttpServletRequest request) throws NamingException {
		setUpDB();
		setUpUser(request.getSession());
		bindData(request);
	}
	private static void setUpDB() throws NamingException {
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");

        HashMap properties = new HashMap();
        properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WebTest2", properties);
        em = emf.createEntityManager();
	}
	private static void setUpUser(HttpSession session) {
		Object loggedIn = session.getAttribute("logged_in");
		if(loggedIn != null && (Boolean)loggedIn) {
			visitor = new AdminVisitor(session, Admin.getById((Integer)session.getAttribute("admin_id")));
		} else {
			visitor = new Visitor(session);
		}
	}
	private static void bindData(HttpServletRequest request) {
		request.setAttribute("visitor", visitor);
		request.setAttribute("urlPrefix", urlPrefix);
	}
}
