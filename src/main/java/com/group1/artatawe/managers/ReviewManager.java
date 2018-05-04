package com.group1.artatawe.managers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.group1.artatawe.accounts.Account;
import com.group1.artatawe.artwork.Artwork;
import com.group1.artatawe.listings.Listing;
import com.group1.artatawe.utils.Review;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ReviewManager {

    private static final String REVIEWS_FILE = "reviews.json";
    private final LinkedList<Review> listings = new LinkedList<>();

    /**
     * Construct a new ListingManager
     * Will load all the Listings out of the file
     */
    public ReviewManager() {
        this.loadReviews();
    }

    /**
     * Add a listing to the system. Will automatically be in the state ACTIVE
     *
     * @param artwork - The artwork being sold
     * @param reserve - The minimum amount this item can sell for
     * @param maxBids - The number of bids before the listing is over
     * @param seller  - The seller of the item
     * @return The listing created
     */
    public Listing addListing(Artwork artwork, double reserve, int maxBids, Account seller) {
        int id = listings.size() + 1;

        Listing l = new Listing(id, artwork, reserve, maxBids, seller.getUserName(), System.currentTimeMillis());
        this.listings.add(l);

        seller.getHistory().addSellingListing(l);

        this.saveListingsFile();
        return l;
    }

    /**
     * Get all the Listings in the system, regardless of state
     * @return All listings
     */
    public List<Listing> getAllListings() {
        return this.listings;
    }

    /**
     * Save all the Listings back to the file
     */
    public void saveListingsFile() {
        StringBuilder data = new StringBuilder();

        this.listings.forEach(listing -> data.append(listing.toJsonObject().toString() + "\n"));

        File listingFile = new File(REVIEWS_FILE);

        try {
            Files.write(listingFile.toPath(), data.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open file and load all the listings out of the file
     */
    private void loadReviews() {
        File file = new File(REVIEWS_FILE);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);

            while(scanner.hasNextLine()) {
                String nextLine = scanner.nextLine().trim();

                if(! nextLine.isEmpty()) {
                    this.loadReview(nextLine);
                }
            }
        } catch (FileNotFoundException e) {
            try {
                new File(REVIEWS_FILE).createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Loads a Listing from a Json String into the system
     *
     * @param jsonString - The Json String that is turned into a Listing
     */
    private void loadReview(String jsonString) {
        JsonParser jp = new JsonParser();
        try {
            JsonObject jo = jp.parse(jsonString).getAsJsonObject();

            Review review = new Review(jo);

            this.listings.add(listing);
        } catch(Exception e) {
            System.out.println("Parse error on string: \n" + jsonString + "\nThe listing has not been loaded.");
            System.out.println(e.getMessage());
        }
    }
}
