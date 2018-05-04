package com.group1.artatawe.managers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.group1.artatawe.accounts.Account;
import com.group1.artatawe.utils.Review;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Used to process all reviews
 */
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
     * @param dateCreated Review date
     * @param title Title of artwork for which review is given
     * @param reviewText The review
     * @param sellerRating Buyers rating of seller
     * @param seller The seller
     * @param listingId The ID of listing for which review is given
     * @return The review created
     */
    public Review addReview(long dateCreated, String title, String reviewText,
                            int sellerRating, Account seller, int listingId) {
        int id = reviews.size() + 1;

        Review r = new Review(id, seller.getUserName(), dateCreated,
                title, reviewText, sellerRating, listingId);
        this.reviews.add(r);
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
     *
     * @param seller The seller
     * @return All reviews of specified seller
     */
    public List<Review> getSellersReviews(Account seller) {
        List<Review> reviews = this.reviews.stream()
                .filter(x -> x.getSellerUsername().equals(seller.getUserName()))
                .sorted(Comparator.comparing(o -> o.getDateCreated())).collect(Collectors.toList());
        Collections.reverse(reviews);
        return reviews;
    }

    /**
     * Calculate the overall sellers rating from reviews
     *
     * @param seller The seller
     * @return Return sellers rating
     */
    public double getSellerRating(Account seller) {
        List<Review> sellersReviews = getSellersReviews(seller);
        double noOfReviews = sellersReviews.size();
        double ratingSum = sellersReviews.stream().mapToInt(x -> x.getSellerRating()).sum();

        if (noOfReviews > 0 && ratingSum > 0) {
            double rating = ratingSum / noOfReviews;
            return round(rating, 2);
        } else {
            return 0;
        }
    }

    /**
     * Check if a review already exists for a listing
     * @param listingId The id of listing
     * @return true if review exists, otherwise false
     */
    public boolean doesReviewExist(int listingId) {
        return reviews.stream().anyMatch(x -> x.getListingId() == listingId);
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

    /**
     * Rounds number to specified number of decimal place
     *
     * @param value  The value to be rounder
     * @param places Number of decimal places to be rounded off to
     * @return Rounded value
     */
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
