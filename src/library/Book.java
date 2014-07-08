package library;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Table;
@Entity
public class Book implements Serializable {
	@javax.persistence.Id @GeneratedValue Integer Id;
	private String name;
	private String author;
	private String description;
	private Integer availableCount;
	private Integer totalCount;
	
	public Integer getAvailableCount() {
		return availableCount;
	}
	public void setAvailableCount(Integer availableCount) {
		this.availableCount = availableCount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public static boolean exists(String name, String author, String description) {
		Query q = Global.em.createQuery("select b from Book b where lower(b.name)=lower(:name) and lower(b.author)=lower(:author) and lower(b.description)=lower(:description)");
		q.setParameter("name", name);
		q.setParameter("author", author);
		q.setParameter("description", description);
		try {
			return (Book) q.getSingleResult() != null;
		} catch (NoResultException e) {
			return false;
		}
	}
	public static Book[] getAll() {
		Query q = Global.em.createQuery("select b from Book b");
		List<Book> list = q.getResultList();
		Book[] books = new Book[list.size()];
		list.toArray(books);
		return books;
	}
}
