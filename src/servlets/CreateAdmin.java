package servlets;


import java.io.IOException;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import library.Admin;
import library.BCrypt;

import org.eclipse.persistence.config.PersistenceUnitProperties;

/**
 * Servlet implementation class CreateAdmin
 */
public class CreateAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			create("admin", "123456");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void create(String username, String password) throws NamingException {
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");

        HashMap properties = new HashMap();
        properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WebTest2", properties);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassHash(BCrypt.hashpw(password, BCrypt.gensalt()));
        em.persist(admin);
        em.getTransaction().commit();
	}
}
