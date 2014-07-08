package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.AdminVisitor;
import library.Global;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GenerateGlobals
 */
public class GenerateGlobals extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateGlobals() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap objects = new HashMap();
		objects.put("urlPrefix", Global.urlPrefix);
		objects.put("loggedIn", Global.visitor.isLoggedIn());
		if(Global.visitor.isLoggedIn()) {
			objects.put("username", ((AdminVisitor)Global.visitor).getUsername());
			HashMap<String, String> links = new HashMap<String, String>();
			links.put("Books", "Books");
			links.put("Add Book", "books/Add");
			links.put("Users", "users");
			links.put("Add User", "users/Add");
			links.put("Booking", "booking");
			objects.put("headerLinks", links);
		}
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(objects));
	}
}
