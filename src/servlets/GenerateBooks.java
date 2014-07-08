package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.Book;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GenerateBooks
 */
public class GenerateBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().create();
		HashMap<String, Book[]> hm = new HashMap<String, Book[]>();
		Book[] books = Book.getAll();
		/*TreeSet<HashMap<String, String>> objects = new TreeSet<HashMap<String, String>>();
		for(int i=0;i<books.length;++i) {
			HashMap<String, String> pairs = new HashMap<String, String>();
			pairs.put("name", books[i].getName());
			pairs.put("author", books[i].getAuthor());
			pairs.put("description", books[i].getDescription());
			pairs.put("count", String.format("%d/%d", books[i].getAvailableCount(), books[i].getTotalCount()));
			objects.add(pairs);
		}*/
		hm.put("model", books);
		response.getWriter().print(gson.toJson(hm));
	}

}
