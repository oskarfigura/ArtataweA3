package com.group1.artatawe.accounts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.group1.artatawe.artwork.Gallery;
import com.group1.artatawe.listings.Listing;
import com.group1.artatawe.utils.ImageUtil;

import javafx.scene.image.Image;

/**
 * Represent a single account / user in the system
 */
public class Account {
	
	public static final String AVATAR_FOLDER = "avatars";

	private String userName;
	private String firstName;
	private String lastName;
	private String mobileNum;
	private Address address;
	private Image avatar;
	private long lastLogin;
	
	private final AccountHistory history = new AccountHistory(this);
	private final LinkedList<String> favAccounts = new LinkedList<>();
	private final LinkedList<Gallery> userGalleries = new LinkedList<>();
	
	static {
		//Create the avatar folder (if it doesn't exist)
		new File(AVATAR_FOLDER).mkdirs();
	}

	/**
	 * Construct a new Account
	 * 
	 * @param userName	- The unique username of the account
	 * @param firstName - The first name of the user
	 * @param lastName  - The last name of the user
	 * @param mobileNum - The mobile number of the person
	 * @param address   - The address of the user
	 * @param avatar    - The path to the avatar image
	 * @param lastLogin - The time the user last logged in
	 */
	public Account(String userName, String firstName, String lastName, 
			String mobileNum, Address address, Image avatar, long lastLogin) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNum = mobileNum;
		this.address = address;
		this.avatar = avatar;
		this.lastLogin = lastLogin;
	}
	
	/**
	 * Construct a new Account
	 * 
	 * @param json - The json object to load the account from
	 */
	public Account(JsonObject json) {
		this.loadFromJson(json);
	}

	/**
	 * Get the unique username of this account
	 * @return The username
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * Get the first name of the user
	 * @return The first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Get the last name of the user
	 * @return The last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Get the mobile number of the user
	 * @return The mobile number
	 */
	public String getMobileNum() {
		return this.mobileNum;
	}

	/**
	 * Get the address of the user
	 * @return The address
	 */
	public Address getAddress() {
		return this.address;
	}

	/**
	 * Get the path to the image of the avatar
	 * @return The path to the avatar
	 */
	public Image getAvatar() {
		return this.avatar;
	}

	/**
	 * Get this account's history
	 * @return The AccountHistory
	 */
	public AccountHistory getHistory() {
		return this.history;
	}

	/**
	 * Get all the favourite accounts of a user
	 * @return A list containing all the favourite account's usernames
	 */
	public LinkedList<String> getFavAccounts() {
		return this.favAccounts;
	}

	/**
	 * Get all the galleries of the user
	 * @return A list containing all the galleries of the user
	 */
	public LinkedList<Gallery> getUserGalleries() {
		return userGalleries;
	}

	/**
	 * Get the last time this user logged in
	 * @return Last login time, in milliseconds
	 */
	public long getLastLogin() {
		return this.lastLogin;
	}

	/**
	 * Mark an account as favourite
	 * @param account - the account to favourite
	 */
	public void addFavAccount(Account account) {
		this.favAccounts.add(account.getUserName());
	}

	/**
	 * Unmark an account as a favourite
	 * @param account - The account to unfavourite
	 */
	public void removeFavAccounts(Account account) {
		if(this.favAccounts.contains(account.getUserName())) {
			this.favAccounts.remove(account.getUserName());
		}
	}

	/**
	 * Check if an account is to be flagged as favourite
	 * 
	 * @param account - The username of the account to check
	 * @return True if account if favourite, else False
	 */
	public boolean isFavAccount(Account account) {
		return this.favAccounts.stream()
				.anyMatch(favUser -> favUser.equals(account.getUserName()));
	}

	/**
	 *
	 * @param galleryName
	 * @return
	 */
	public boolean checkGallery(String galleryName) {
		return this.userGalleries.stream()
				.anyMatch(x -> x.getName().equalsIgnoreCase(galleryName));
	}

	/**
	 * Adds a new gallery to the users profie
	 * @param g
	 */
	public void addGallery(Gallery g) {
		this.userGalleries.add(g);
	}

	/**
	 * Finds the gallery the user has picked and then assignes a new listing to it
	 * @param galleryName
	 * @param l
	 */
	public void addToGallery(String galleryName, Listing l) {
		this.userGalleries.stream()
				.filter(x -> x.getName().equalsIgnoreCase(galleryName))
				.findFirst()
				.get()
				.addListing(l);
	}

	/**
	 * permanently removes a gallery from the users profile
	 * @param galleryName
	 */
	public void removeGallery(String galleryName) {
		Gallery g = this.userGalleries.stream().filter(x -> x.getName().equalsIgnoreCase(galleryName))
				.findFirst().get();
		this.userGalleries.remove(g);
	}

	/**
	 * Set the first name of this account
	 * @param firstName - The new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Set the last name of this account
	 * @param lastName - The new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Set the mobile number of this account
	 * @param mobileNum - The new mobile number
	 */
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	
	/**
	 * Set the Address
	 * @param address - The new address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	
	/**
	 * Set the Avatar
	 * @param avatar - The new avatar
	 */
	public void setAvatar(Image avatar) {
		this.avatar = avatar;
	}
	
	/**
	 * Log an account in. Will update the last login time
	 */
	public void login() {
		this.lastLogin = System.currentTimeMillis();
	}

	/**
	 * Convert this account into a Json string
	 * @return The generated Json String
	 */
	public JsonObject toJsonObject() {
		JsonObject jo = new JsonObject();

		//Add basic attributes
		jo.addProperty("username", this.userName);
		jo.addProperty("firstname", this.firstName);
		jo.addProperty("lastname", this.lastName);
		jo.addProperty("mobile", this.mobileNum);
		jo.addProperty("lastlogin", this.lastLogin);
		
		//Save the avatar and add the path to the json
		String avatarFileName = this.userName + ".png";
		
		File imageFile = new File(AVATAR_FOLDER, avatarFileName);
		
		try {
			ImageUtil.writeImageToFile(imageFile, this.avatar);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		jo.addProperty("avatar", avatarFileName);

		//Add the address
		JsonArray addrArray = new JsonArray();
		addrArray.add(this.address.getLine(1));
		addrArray.add(this.address.getLine(2));
		addrArray.add(this.address.getLine(3));
		addrArray.add(this.address.getCity());
		addrArray.add(this.address.getPostcode());

		jo.add("address", addrArray);
		
		//Add AccountHistory
		for(Entry<String, JsonElement> entry : this.history.toJsonObject().entrySet()) {
			jo.add(entry.getKey(), entry.getValue());
		}
		
		//Add the fav accounts
		JsonArray favArray = new JsonArray();
		for(String fav : this.favAccounts) {
			favArray.add(fav);
		}

		jo.add("favaccounts", favArray);

		/*
		 * Hardcoded data for testing
		 */
		//Gallery g = new Gallery(Main.accountManager.getLoggedIn(), "MyGallery");
		//g.addArtwork(Main.listingManager.getListing(1).getArtwork());
		//userGalleries.add(g);

		JsonArray galleriesArray = new JsonArray();
		userGalleries.stream().forEach(x -> {
			JsonObject j = x.toJsonObject();
			galleriesArray.add(j); // Adding a json object into an array
		});

		//TODO -> Needs some testing
		jo.add("galleries", galleriesArray);

		return jo;
	}
	
	/**
	 * Load the account back from a JsonObject
	 * @param jo - The Json Object
	 */
	private void loadFromJson(JsonObject jo) {
		//Load all the basic information
		this.userName  = jo.get("username").getAsString();
		this.firstName = jo.get("firstname").getAsString();
		this.lastName = jo.get("lastname").getAsString();
		this.mobileNum = jo.get("mobile").getAsString();
		this.lastLogin = jo.get("lastlogin").getAsLong();
		
		try {
			this.avatar = ImageUtil.loadImage(new File(AVATAR_FOLDER, jo.get("avatar").getAsString()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Load the Address
		JsonArray jarray = jo.get("address").getAsJsonArray();
		String line1 = jarray.get(0).getAsString();
		String line2 = jarray.get(1).getAsString();
		String line3 = jarray.get(2).getAsString();
		String city = jarray.get(3).getAsString();
		String postcode = jarray.get(4).getAsString();
		
		this.address = new Address(line1, line2, line3, city, postcode);

		//Load account history
		this.history.loadFromJson(jo);
		
		//Load fav accounts
		Iterator<JsonElement> favAccountIt = jo.get("favaccounts").getAsJsonArray().iterator();
		favAccountIt.forEachRemaining(nextUser -> this.favAccounts.add(nextUser.getAsString()));

	}

    /**
     * Loads all the galleries of a particular user
     * @param jo
     */
	public void loadGalleries(JsonObject jo, Account acc) {
	    /*
	        From the Json object passed, the array with the galleries is read in
	        From then on, since it also contains JsonObject, we get all of them
	        and read in the data they contain making into a gallery.
	     */
        JsonArray galleryArray = jo.getAsJsonArray("galleries");
        for (int i = 0; i < galleryArray.size(); i++) {
            JsonObject object = galleryArray.get(i).getAsJsonObject();
            Gallery g = new Gallery(object, acc);
            this.userGalleries.add(g);
        }
    }
}
