package library;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity

public class User implements Serializable {

	
	private String name;
    @Id
    @GeneratedValue
	private Integer facultyNumber;
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public Integer getFacultyNumber() {
		return this.facultyNumber;
	}

	public void setFacultyNumber(Integer facultyNumber) {
		this.facultyNumber = facultyNumber;
	}
    public boolean exists(String name, int facultyNumber) {
    	Query q = Global.em.createQuery("select facultyNumber from user where facultyNumber=:fn and lower(name)=lower(:name)");
    	try {
    		return q.getSingleResult() != null;
    	} catch(NoResultException e){
    		return false;
    	}
    }
}
