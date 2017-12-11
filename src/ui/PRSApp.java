package ui;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import business.Product;
import business.PurchaseRequest;
import business.PurchaseRequestDB;
import business.PurchaseRequestLineItem;
import business.Status;
import business.StatusDB;
import business.User;
import business.UserDB;
import business.Vendor;
import business.VendorDB;
import db.ProductDB;
import util.Console;

public class PRSApp {
	private static HashMap<Integer, Status>statusMap;
	private static User validatedUser = null;
	public static void main(String[] args) { 
		initializeStatusMap();
		System.out.println("Welcome to PRS JPA!\n");
		String choice = "";
		initialDisplay();
		while (!choice.equalsIgnoreCase("exit")) {
			if (choice.equalsIgnoreCase("")) {
				choice = Console.getString("Option?:  ");
				login();
			}
			displayMenu();
			choice = Console.getString("Option?:  ");

			switch (choice) {
			case "getuser":
				getUserById();
				break;
			case "all":
				getAllUsers();
				break;
			case "add":
				addUser();
				break;
			case "getvendor":
				getVendorById();
				break;
			case "getpurch":
				getPurchById();
				break;
			case "getprod":
				getProdById();
				break;
			case "getallprod":
				getAllProducts();
				break;
			case "del":
				deleteUser();
				break;
			case "update":
				updateUser();
				break;
			case "status":
				getAllStatus();
				//getPR();
				break;
			case "newpr": // sequence table error, ask S for help
				addPurchaseRequest();
				break;
			case "logout": 
				logout();
				initialDisplay();
				choice = Console.getString("Option?:  ");
				while (true) {
					if (choice.equalsIgnoreCase("exit")) {
						break;
					}
				}
			}
		}
		


	}
	private static void initializeStatusMap() {
		statusMap = new HashMap<>();
		ArrayList<Status> statusID = StatusDB.getAllStatuses();
		for (Status s: statusID) {
			statusMap.put(s.getId(), s);
		}
		System.out.println();
	}
		
	public static void initialDisplay() {
		
		System.out.println("\nMenu:");
		System.out.println("=====================");
		System.out.println("login - User Login");
		System.out.println("exit - Exit application");
		System.out.println();
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
		System.out.println("status - get all status options");
		System.out.println("del  - Delete a User");
		System.out.println("update  - update a username");
		System.out.println("exit - Exit application");
		System.out.println("logout - log out of application");
		System.out.println();
	}
	public static void login() {
		System.out.println("User Login");
	while(true) {
		String userName = Console.getString("userName:  ");//
		String password= Console.getString("password:  ");//
		validatedUser = UserDB.authenticateUser(userName, password);
		if (validatedUser!=null) {
			System.out.println("Success:  user logged in.");
			System.out.println(validatedUser);
			break;
		} else {
			System.out.println("Login failure:  no user for given userName & pwd combo.");
			}
		}
	}
	public static void logout() {
		String uString = validatedUser.getUserName();
		validatedUser = null;
		if(uString != null) {
			System.out.println("User '"+uString+"' logged out.");
			uString = null;
		}
//		}else {
//			System.out.println("User '"+uString+"' could not log out");
//		}
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
		Status s = statusMap.get(statusID);
		PurchaseRequest p = new PurchaseRequest(description, justification, deliveryMode, s, total);
		p.setSubmittedDate(new Timestamp(System.currentTimeMillis()));
		p.setDateNeeded(new Timestamp(System.currentTimeMillis()));
		User u = (UserDB.getUserById(userId));
		p.setUserID(u);
		p.isActive();
		addPRLI();
		p.getPrLineItems();
		p.setUpdatedByUser(u);
		if (PurchaseRequestDB.addPurchaseRequest(p)) {
			System.out.println("Purchase Request " + p.getUserID() + "successfully added");
		}
		System.out.println();
	}
	private static void addPRLI() {
		System.out.println("\n Please enter line items.");
		int quantity = Console.getInt("Enter quantity: ");
		int pID = Console.getInt("Enter product ID: ");
		PurchaseRequestLineItem prli = new PurchaseRequestLineItem();
		prli.setQuantity(quantity);
		prli.setProductID(pID);
		//prli.setPurchaserequestID(pID);
		
		
	}
	private static void getPR() {
		int prID = Console.getInt("Enter id of pr to retrieve: ");
		PurchaseRequest pr = PurchaseRequestDB.getPurchaseRequestById(prID);
//		String desc = statusMap.get(pr.getStatus());
//		System.out.println("PR: " + pr + " status desc = "+desc);
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
	private static void getAllStatus() {
		ArrayList<Status> status = StatusDB.getAllStatuses();
		System.out.println("\nAll users: ");
		for (Status s : status) {
			System.out.println(s);
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