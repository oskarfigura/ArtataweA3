package com.group1.artatawe.artwork;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.group1.artatawe.Main;
import com.group1.artatawe.accounts.Account;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.group1.artatawe.listings.Listing;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Represents a gallery of artworks curated by a user
 * @author Adam Payne
 * @version 1.0
 */
public class Gallery {
    //Properties
    private ArrayList<Listing> listings = new ArrayList<>();
    private Account user;
    private String name;

    /**
     * Create a new gallery
     * @param user - The user who created the gallery
     * @param name - The name of the gallery
     */
    public Gallery(Account user, String name) {
        this.user = user;
        this.name = name;
    }

    /**
     * Read up and construct a gallery
     * @param jo
     */
    public Gallery(JsonObject jo, Account acc) {
        this.loadFromJson(jo, acc);
    }

    /**
     *  Transfors a gallery object into a Json so it could later be saved in the data files
     * @return
     */
    public JsonObject toJsonObject() {
        JsonObject jo = new JsonObject();

        jo.addProperty("userName", this.user.getUserName());
        jo.addProperty("galleryName", this.name);

        JsonArray galleryArray = new JsonArray();
        /*
        Previous method using artworks instead of listings
        
        this.listings.stream().forEach(x -> {
            Main.listingManager.getAllListings().stream().forEach(y -> {
                if (y.getArtwork() == x) {
                    int id = y.getID();
                    galleryArray.add(id);
                }
            });
        });
        */

        this.listings.stream().forEach(x -> {
            int id = x.getID();
            galleryArray.add(id);
        });
        jo.add("list", galleryArray);

        return jo;
    }

    /**
     * Loads a user gallery from a JsonObject
     * @param jo
     */
    public void loadFromJson(JsonObject jo, Account acc) {
        try {
            this.user = acc;
        } catch (NullPointerException e) {
            System.out.println("Ops no account, couldn't load gallery");
        }
        this.name = jo.get("galleryName").getAsString();

        /*

        Iterator<JsonElement> it = jo.get("list").getAsJsonArray().iterator();
        it.forEachRemaining(x -> {
            int id = x.getAsInt();
            Main.listingManager.getAllListings().stream().forEach(y -> {
                if (y.getID() == id) {
                    this.listings.add(y.getArtwork());
                }
            });
        });
        */

        Iterator<JsonElement> it = jo.get("list").getAsJsonArray().iterator();
        it.forEachRemaining(x -> {
            int id = x.getAsInt();
            Listing l = Main.listingManager.getListing(id);
            this.listings.add(l);
        });
    }
    
    /**
     * Get the user who created the gallery
     * @return The account of the user who created the gallery
     */
    public Account getUser() {
        return user;
    }

    /**
     * Get the name of the gallery
     * @return The name of the gallery
     */
    public String getName() {
        return name;
    }

    /**
     * Get the listings contained in the gallery
     * @return The listings in the gallery, in an ArrayList
     */
    public ArrayList<Listing> getListings() {
        return listings;
    }

    /**
     * Add a listing to the gallery. Duplicate entries will be ignored
     * @param listing The listing to be added to the gallery
     */
    public void addListing(Listing listing) {
        //Don't add the listing if it's already in the gallery
        if (!listings.contains(listing)) {
            listings.add(listing);
        }
    }

    /**
     * Remove a listing from the gallery
     * @param listing The listing to remove from the gallery
     */
    public void removeListing(Listing listing) {
        listings.remove(listing);
    }

    /**
     * Check whether the gallery contains a specific listing
     * @param listing the listing to check for
     * @return True if the gallery contains the given listing
     */
    public boolean containsListing(Listing listing) {
        return listings.contains(listing);
    }
}
