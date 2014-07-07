package library;


import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Admin
 *
 */
@Entity

public class Admin implements Serializable {

    @Id
    @GeneratedValue
	private Integer id;
	private String username;
	private String passHash;
	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}   
	public String getPassHash() {
		return this.passHash;
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}

	public static Admin getById(int id) {
		Query q = Global.em.createQuery("select a from Admin a where a.id=:id");
		q.setParameter("id", id);
		try {
			return (Admin) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	public static Admin getByUsername(String username) {
		Query q = Global.em.createQuery("select a from Admin a where a.username=:username");
		q.setParameter("username", username);
		try {
			return (Admin) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
