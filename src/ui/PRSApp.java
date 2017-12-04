package ui;

import java.sql.Timestamp;
import java.util.ArrayList;

import business.Product;
import business.PurchaseRequest;
import business.PurchaseRequestDB;
import business.PurchaseRequestLineItem;
import business.User;
import business.UserDB;
import business.Vendor;
import business.VendorDB;
import db.ProductDB;
import util.Console;

public class PRSApp {
	public static void main(String[] args) {
		System.out.println("Welcome to PRS JPA!\n");
		String choice = "";
		while (!choice.equalsIgnoreCase("exit")) {
			displayMenu();
			choice = Console.getString("Option?:  ");
			
			if (choice.equalsIgnoreCase("getuser")) {
				getUserById();
			}else if (choice.equalsIgnoreCase("all")) {
				getAllUsers();
			}else if (choice.equalsIgnoreCase("add")) {
				addUser();
			}else if (choice.equalsIgnoreCase("getvendor")) {
				getVendorById();
			}else if (choice.equalsIgnoreCase("getpurch")) {
				getPurchById();
			}else if (choice.equalsIgnoreCase("getprod")) {
				getProdById();
			}else if (choice.equalsIgnoreCase("getallprod")) {
				getAllProducts();
			}else if (choice.equalsIgnoreCase("del")) {
				deleteUser();
			}else if (choice.equalsIgnoreCase("update")) {
				updateUser();
			}else if (choice.equalsIgnoreCase("newpr")) { //sequence table error, ask S for help
				addPurchaseRequest();
			}else {
				if (!choice.equalsIgnoreCase("exit")) {
				System.out.println("Input not accepted. Please provide valid choice.");
				}
			}
		}


	}
	private static void deleteUser() {
		int userId = Console.getInt("Enter userID to delete: ");
		User u = UserDB.getUserById(userId);
		if(UserDB.deleteUser(u)) {
			System.out.println("User " + u.getUserName() + " successfully deleted.");
		}
	}
	private static void updateUser() {
		int userId = Console.getInt("Enter userID to update: ");
		User u = UserDB.getUserById(userId);
		String fName = Console.getString("Enter new value for first name: ");
		u.setFirstName(fName);  //must set the first name in pojo to successfully update the column
		if(UserDB.updateUser(u)) {
			System.out.println("User " + u.getUserName() + " successfully updated.");
		}
	}
	public static void displayMenu() {
		
		System.out.println("\nMenu:");
		System.out.println("=====================");
		System.out.println("getuser  - get User by ID");
		System.out.println("all  - Display All Users");
		System.out.println("add  - Add a User");
		System.out.println("newpr  - Add a purchase request");
		System.out.println("getvendor - get a vendor by ID");
		System.out.println("getpurch - get a purchaserequest by ID");
		System.out.println("getprod - get a product by ID");
		System.out.println("getallprod - get all products");
		System.out.println("del  - Delete a User");
		System.out.println("update  - update a username");
		System.out.println("exit - Exit application");
		System.out.println();
	}
	private static void addUser() {
		System.out.println("\nWe are going to be adding users.");
		String userName = Console.getString("Enter uname: ");
		String password = Console.getString("Pword: ");
		String firstName = Console.getString("fName: ");
		String lastName = Console.getString("lName: ");
		String phoneNumber = Console.getString("pNumber: ");
		String email = Console.getString("email: ");
		boolean reviewer = Console.getBoolean("reviewer: ");
		boolean admin = Console.getBoolean("admin: ");
		User a = new User(userName, password, firstName, lastName, phoneNumber, email, reviewer, admin);
		a.setDate(new Timestamp(System.currentTimeMillis()));
		if (UserDB.addUser(a)) {
			System.out.println("User " + a.getUserName() + " successfully added");
		}
		System.out.println();
	}
	private static void addPurchaseRequest() {
		System.out.println("\nWe are going to be adding a purchase request.");
		int userId = Console.getInt("Enter userID: ");
		String description = Console.getString("Enter description: ");
		String justification = Console.getString("Enter justification: ");
		String deliveryMode = Console.getString("Enter delivery Mode: ");
		int statusID = Console.getInt("Enter StatusID: ");
		double total = Console.getDouble("Enter total: ");
		//ArrayList<PurchaseRequestLineItem> prLineItems;
		PurchaseRequest p = new PurchaseRequest(description, justification, deliveryMode, statusID, total);
		p.setSubmittedDate(new Timestamp(System.currentTimeMillis()));
		p.setDateNeeded(new Timestamp(System.currentTimeMillis()));
		User u = (UserDB.getUserById(userId));
		p.setUserID(u); //implement a login feature
		p.isActive();
		p.getPrLineItems();
		p.setUpdatedByUser(u);;
		if (PurchaseRequestDB.addPurchaseRequest(p)) {
			System.out.println("Purchase Request " + p.getUserID() + "successfully added");
		}
		System.out.println();
	}

	private static void getUserById() {
		int userID = Console.getInt("\nEnter userID to retrieve: ");
		User u = UserDB.getUserById(userID);
		System.out.println("User details: " + u);
	}

	private static void getVendorById() {
		int vendorID = Console.getInt("\nEnter vendorID to retrieve: ");
		Vendor v = VendorDB.getVendorById(vendorID);
		System.out.println("Vendor details: " + v);
	}

	private static void getPurchById() {
		int purchID = Console.getInt("\nEnter purchID to retrieve: ");
		PurchaseRequest p = PurchaseRequestDB.getPurchaseRequestById(purchID);
		System.out.println("Purchase Request details: " + p);
	}

	private static void getProdById() {
		int productID = Console.getInt("\nEnter productID to retrieve: ");
		Product pd = ProductDB.getProductById(productID);
		System.out.println("Purchase Request details: " + pd);
	}
	private static void getAllUsers() {
		ArrayList<User> users = UserDB.getAllUsers();
		System.out.println("\nAll users: ");
		for (User u : users) {
			System.out.println(u);
		}
	}
	private static void getAllProducts() {
		ArrayList<Product> product = ProductDB.getAllProducts();
		System.out.println("\nAll products: ");
		for (Product p : product) {
			System.out.println(p);
		}
	}
}