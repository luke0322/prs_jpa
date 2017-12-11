package business;

import javax.persistence.Entity;
import javax.persistence.Id;

import util.Console;

@Entity
public class Status {
	@Id
	private int id;
	private String description;
	
	public Status() {
		this.description = "";
	}
	public Status(int id, String description) {
		this.id = id;
		this.description = description;
	}
	public Status(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
@Override
public String toString() {
	return "Status, id = "+id+", description = "+description;
	}
//if(choice.equalsIgnoreCase("")) {
//choice = Console.getString("Option?:  ");
//login();
//}
//displayMenu();
//choice = Console.getString("Option?:  ");
//if (choice.equalsIgnoreCase("getuser")) {
//getUserById();
//}else if (choice.equalsIgnoreCase("all")) {
//getAllUsers();
//}else if (choice.equalsIgnoreCase("add")) {
//addUser();
//}else if (choice.equalsIgnoreCase("getvendor")) {
//getVendorById();
//}else if (choice.equalsIgnoreCase("getpurch")) {
//getPurchById();
//}else if (choice.equalsIgnoreCase("getprod")) {
//getProdById();
//}else if (choice.equalsIgnoreCase("getallprod")) {
//getAllProducts();
//}else if (choice.equalsIgnoreCase("del")) {
//deleteUser();
//}else if (choice.equalsIgnoreCase("update")) {
//updateUser();
//}else if (choice.equalsIgnoreCase("status")) {
////getAllStatus();
//
//getPR();
//}else if (choice.equalsIgnoreCase("newpr")) { //sequence table error, ask S for help
//addPurchaseRequest();
//}else if (choice.equalsIgnoreCase("logout")) { //sequence table error, ask S for help
//logout();
//initialDisplay();
//choice = Console.getString("Option?:  ");
//if(choice.equalsIgnoreCase("exit")) {
//	break;
//}
//}else {
//if (!choice.equalsIgnoreCase("exit")) {
//System.out.println("Input not accepted. Please provide valid choice.");
//}
//}
}


