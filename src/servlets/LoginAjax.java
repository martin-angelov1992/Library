package servlets;

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

/**
 * Servlet implementation class LoginAjax
 */
public class LoginAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAjax() {
        super();
        // TODO Auto-generated constructor stub
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HashMap<String, String> data = new HashMap<String, String>();
		if(Global.visitor.tryLogin(username, password)) {
			data.put("error", "");
		} else {
			data.put("error", "Invalid username or password");
		}
		Gson gson = new GsonBuilder().create();
		response.getWriter().write(gson.toJson(data));
	}

}
