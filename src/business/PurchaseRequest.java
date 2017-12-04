package business;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import business.PurchaseRequestLineItem;

@Entity
public class PurchaseRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //it is managing the table IDENTITY
	private int id;
	@ManyToOne
	@JoinColumn(name = "UserID")
	private User userID; // User user, int userID
	private String description;
	private String justification;
	//////
	private Timestamp dateNeeded;
	private String deliveryMode;
	private int statusID;
	private double total;
	private Timestamp submittedDate;
	private boolean isActive;
	@ManyToOne
	@JoinColumn(name ="UpdatedByUser")
	private User updatedByUser = userID;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "PurchaseRequestID")
	private ArrayList<PurchaseRequestLineItem> prLineItems;


	public PurchaseRequest() {
		id = 0;
		description = "";
		statusID = 1;
		total = 0.0;
		submittedDate = null;
		prLineItems = new ArrayList<>();
		justification = "";
		deliveryMode = "";
		dateNeeded = null;
		isActive = true;
	}

	public PurchaseRequest(int id,User userID,String description, String justification, String deliveryMode, int statusID,
			double total) { 
		super();
		this.id = id;
		this.userID = userID;
		this.description = description;
		this.justification = justification;
		this.deliveryMode = deliveryMode;
		this.statusID = statusID;
		this.total = total;
		prLineItems = new ArrayList<PurchaseRequestLineItem>();
	}
	public PurchaseRequest(String description, String justification, String deliveryMode, int statusID,
			double total) { 
		setDescription(description);
		setJustification(justification);
		setDeliveryMode(deliveryMode);
		setStatus(statusID);
		setTotal(total);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUserID() {
		return userID;
	}

	public void setUserID(User user) {
		this.userID = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Timestamp getDateNeeded() {
		return dateNeeded;
	}

	public void setDateNeeded(Timestamp dateNeeded) {
		this.dateNeeded = dateNeeded;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public int getStatus() {
		return statusID;
	}

	public void setStatus(int status) {
		this.statusID = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Timestamp getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Timestamp submittedDate) {
		this.submittedDate = submittedDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public User getUpdatedByUser() {
		return updatedByUser;
	}
	public void setUpdatedByUser(User updatedByUser) {
		this.updatedByUser= updatedByUser;	
	}
	public ArrayList<PurchaseRequestLineItem> getPrLineItems() {
		return prLineItems;
	}

	public void setPrLineItems(ArrayList<PurchaseRequestLineItem> prLineItems) {
		this.prLineItems = prLineItems;
	}

	@Override
	public String toString() {
		return "\nUser: id = " + id + ", userID=" + userID + ", description = " + description + ", justification = "
				+ justification + ", " + "dateNeeded = " + dateNeeded + ", deliveryMode = " + deliveryMode
				+ ", Status =" + statusID + "," + "total= " + total + ", submittedDate = " + submittedDate
				+ "\n, prLineItems=" + prLineItems + "]";

	}

}
