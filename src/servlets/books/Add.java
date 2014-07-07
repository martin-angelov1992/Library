package servlets.books;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.media.jfxmedia.logging.Logger;

import library.Book;
import library.Global;

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
		request.getRequestDispatcher("/books/add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name, author, description;
		Integer amount;
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
		Book book = new Book();
		book.setName(name);
		book.setAuthor(author);
		book.setDescription(description);
		book.setAvailableCount(amount);
		book.setTotalCount(amount);
		Global.em.getTransaction().begin();
        Global.em.persist(book);
        Global.em.getTransaction().commit();
        request.setAttribute("created", true);
        request.getRequestDispatcher("/books/add.jsp").forward(request, response);
	}

}
