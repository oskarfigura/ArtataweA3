package com.group1.artatawe.artwork;

import com.google.gson.JsonObject;
import com.group1.artatawe.accounts.Account;
import com.group1.artatawe.listings.Listing;

import java.util.ArrayList;

/**
 * Represents a gallery of artworks curated by a user
 * @author Adam Payne
 * @version 1.0.1
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
    public Gallery(JsonObject jo) {
        //TODO -> sort out the JSON
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
     * Add a listing to the gallery
     * @param listing The listing to be added to the gallery
     */
    public void addListing(Listing listing) {
        listings.add(listing);
    }

    /**
     * Remove a listing from the gallery
     * @param listing The listing to be removed from the gallery
     */
    public void removeListing(Listing listing) {
        listings.remove(listing);
    }
}
