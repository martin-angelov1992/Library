package servlets.users;

import java.io.IOException;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import library.Global;
import library.User;

/**
 * Servlet implementation class Add
 */
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Global.handleGlobals(request);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!Global.visitor.isLoggedIn()) {
			response.sendRedirect("/"+Global.urlPrefix+"Login");
			return;
		}
		request.getRequestDispatcher("/users/add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Global.handleGlobals(request);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!Global.visitor.isLoggedIn()) {
			response.sendRedirect("/"+Global.urlPrefix+"Login");
			return;
		}
		String name = request.getParameter("name");
		String facultyNumberStr = request.getParameter("fn");
		if(name == null || facultyNumberStr == null) {
			return;
		}
		int facultyNumber;
		Gson gson = new GsonBuilder().create();
		HashMap<String, String> hm = new HashMap<String, String>();
		try {
			facultyNumber = Integer.parseInt(facultyNumberStr);
			if(facultyNumber < 1) {
				throw new NumberFormatException();
			}
		} catch(NumberFormatException e) {
			hm.put("type", "error");
			hm.put("msg", "Invalid faculty number");
			response.getWriter().print(gson.toJson(hm));
			return;
		}
		User user = new User();
		user.setFacultyNumber(facultyNumber);
		user.setName(name);
		Global.em.getTransaction().begin();
        Global.em.persist(user);
        Global.em.getTransaction().commit();
		hm.put("type", "success");
		hm.put("msg", "User added successfully");
        response.getWriter().print(gson.toJson(hm));
	}

}
