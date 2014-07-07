package library;


import java.io.IOException;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;

import com.sun.javafx.collections.MappingChange.Map;

/**
 * Servlet implementation class Test
 */
public class Test extends HttpServlet {
	EntityManagerFactory emf;
	private static final long serialVersionUID = 1L;
       
    /**
     * @throws NamingException 
     * @see HttpServlet#HttpServlet()
     */
    public Test() throws NamingException {
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");

        HashMap properties = new HashMap();
        properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);
        emf = Persistence.createEntityManagerFactory("WebTest2", properties);
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.createNativeQuery("create table testWhichDB (id number)")
        .executeUpdate();
        et.commit();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
