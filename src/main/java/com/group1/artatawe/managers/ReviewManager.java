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
import java.util.stream.Collectors;

public class ReviewManager {

    private static final String REVIEWS_FILE = "reviews.json";
    private final LinkedList<Review> reviews = new LinkedList<>();

    /**
     * Construct a new ListingManager
     * Will load all the Listings out of the file
     */
    public ReviewManager() {
        this.loadReviews();
    }

    /**
     * Add a review to the system
     *
     * @param dateCreated
     * @param title
     * @param reviewText
     * @param sellerRating
     * @param seller
     * @return The review created
     */
    public Review addListing(long dateCreated, String title, String reviewText,
                              int sellerRating, Account seller) {
        int id = reviews.size() + 1;

        Review r = new Review(id, seller.getUserName(), dateCreated, title, reviewText, sellerRating);
        this.reviews.add(r);
        //seller.getReviews().addReview(r);
        this.saveReviewsFile();
        return r;
    }

    /**
     * Get all the Reviews in the system
     *
     * @return All listings
     */
    public List<Review> getAllReviews() {
        return this.reviews;
    }

    /**
     * Get all reviews of a specific seller
     * @param seller The seller
     * @return All reviews of specified seller
     */
    public List<Review> getSellersReviews(Account seller) {
        return this.reviews.stream()
                .filter(x -> x.getSellerUsername().equals(seller.getUserName()))
                .collect(Collectors.toList());
    }

    /**
     * Save all the Reviews back to the file
     */
    public void saveReviewsFile() {
        StringBuilder data = new StringBuilder();

        this.reviews.forEach(review -> data.append(review.toJsonObject().toString() + "\n"));

        File reviewsFile = new File(REVIEWS_FILE);

        try {
            Files.write(reviewsFile.toPath(), data.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open file and load all the reviews out of the file
     */
    private void loadReviews() {
        File file = new File(REVIEWS_FILE);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine().trim();

                if (!nextLine.isEmpty()) {
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
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Loads a review from a Json String into the system
     *
     * @param jsonString - The Json String that is turned into a review
     */
    private void loadReview(String jsonString) {
        JsonParser jp = new JsonParser();
        try {
            JsonObject jo = jp.parse(jsonString).getAsJsonObject();
            Review review = new Review(jo);
            this.reviews.add(review);
        } catch (Exception e) {
            System.out.println("Parse error on string: \n" + jsonString + "\nThe review has not been loaded.");
            System.out.println(e.getMessage());
        }
    }
}
