package com.group1.artatawe.artwork;

import com.group1.artatawe.accounts.Account;

import java.util.ArrayList;

/**
 * Represents a gallery of artworks curated by a user
 * @author Adam Payne
 * @version 1.0
 */
public class Gallery {
    //Properties
    private ArrayList<Artwork> artworks = new ArrayList<>();
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
     * Get the artworks contained in the gallery
     * @return The artworks in the gallery, in an ArrayList
     */
    public ArrayList<Artwork> getArtworks() {
        return artworks;
    }

    /**
     * Add an artwork to the gallery
     * @param artwork The artwork to be added to the gallery
     */
    public void addArtwork(Artwork artwork) {
        artworks.add(artwork);
    }

    public void removeArtwork(Artwork artwork) {
        artworks.remove(artwork);
    }
}
