package business;

import java.io.Serializable;

//import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PurchaseRequestLineItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private int quantity;
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int purchaserequestID;
	private int productID;

	public PurchaseRequestLineItem() {
		id = 0;
		purchaserequestID = 0;
		productID = 0;
		quantity = 0;
	}

	public PurchaseRequestLineItem(int quantity, int id, int purchaserequestID, int productID) {
		super();
		this.quantity = quantity;
		this.id = id;
		this.purchaserequestID = purchaserequestID;
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPurchaserequestID() {
		return purchaserequestID;
	}

	public void setPurchaserequestID(int purchaserequestID) {
		this.purchaserequestID = purchaserequestID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	@Override
	public String toString() {
		return "\npurchaseRequestLineItem [id=" + id + ", purchaseRequestID=" + purchaserequestID + ", productID="
				+ productID + ", quantity=" + quantity + "]";
	}
}
