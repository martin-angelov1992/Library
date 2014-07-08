package servlets.books;

import java.io.IOException;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import library.Book;
import library.Global;

/**
 * Servlet implementation class AddAjax
 */
public class AddAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name, author, description;
		Integer amount;
		HashMap<String, String> vars = new HashMap<String, String>();
		try {
			Global.handleGlobals(request);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!Global.visitor.isLoggedIn()) {
			return;
		}
		name = request.getParameter("name");
		author = request.getParameter("author");
		description = request.getParameter("description");
		String amountStr = request.getParameter("amount");
		try {
			amount = Integer.parseInt(request.getParameter("amount"));
		} catch (NumberFormatException e) {
			amount = 1;
		}
		if(name == null || author == null || description == null || amountStr == null) {
			return;
		}
		Gson gson = new GsonBuilder().create();
		if(Book.exists(name, author, description)) {
	        vars.put("type", "error");
	        vars.put("msg", "This book already exists.");
	        response.getWriter().print(gson.toJson(vars));
			return;
		}
		Book book = new Book();
		book.setName(name);
		book.setAuthor(author);
		book.setDescription(description);
		book.setAvailableCount(amount);
		book.setTotalCount(amount);
		Global.em.getTransaction().begin();
        Global.em.persist(book);
        Global.em.getTransaction().commit();
        vars.put("type", "success");
        vars.put("msg", "Book added successfully.");
        response.getWriter().print(gson.toJson(vars));
	}

}
