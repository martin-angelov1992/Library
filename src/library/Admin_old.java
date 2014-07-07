package library;


import javax.servlet.http.HttpSession;

public class Admin_old extends Visitor {
	public Admin_old(HttpSession session) {
		super(session);
	}
	public boolean tryRegisterBook(Book book) {
		return true;
	}
	public void returnBook(Book book) {
		if(book == null) {
			return;
		}
	}
	public void giveBook(Book book) {
		if(book == null) {
			return;
		}
	}
	public boolean addUser(String name, int facultyNumber) {
		if(name == null || facultyNumber < 0) {
			return false;
		}
		return true;
	}
}
