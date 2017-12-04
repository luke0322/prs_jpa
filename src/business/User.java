package business;


import java.io.Serializable;
//import java.time.LocalDate;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Table;
@Entity
//@Table(name="Users")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO) commenting this out.IDENTITY
	private int id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	@Column(name ="IsReviewer")
	private boolean reviewer;
	@Column(name = "IsAdmin")
	private boolean admin;
	private Timestamp dateCreated;
	
	public User() {
		userName = "";
		password = "";
		
	}
	
	public User(int id, String userName, String password, String firstName, String lastName, String phoneNumber,
			String email, boolean reviewer, boolean admin) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phoneNumber;
		this.email = email;
		this.reviewer = reviewer;
		this.admin = admin;
	}

	public User(String un, String pw, String fn, String ln, String pn, String e, boolean m, boolean a) {
		setUserName(un);
		setPassword(pw);
		setFirstName(fn);
		setLastName(ln);
		setPhoneNumber(pn);
		setEmail(e);
		setReviewer(m);
		setAdmin(a);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phone;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phone = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isReviewer() {
		return reviewer;
	}
	public void setReviewer(boolean inReviewer) {
		this.reviewer = inReviewer;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean inAdmin) {
		this.admin = inAdmin;
	}

	public Timestamp getDate() {
		return dateCreated;
	}

	public void setDate(Timestamp date) {
		this.dateCreated = date;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNumber=" + phone + ", email=" + email + ", reviewer="
				+ reviewer + ", admin=" + admin + ", createDate= " + dateCreated + "]";
	}
	
}
