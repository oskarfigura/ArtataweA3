package com.group1.artatawe.utils;

import com.google.gson.JsonObject;

/**
 * Represent a single Review for an auction.
 */
public class Review {
    private final int id;
    private final long dateCreated;
    private final String title;
    private final String reviewText;
    private final String sellerUsername;
    private final int sellerRating;
    private final int listingId;

    /**
     * Construct a new Review.
     *
     * @param id ID of the review
     * @param dateCreated Date of the review
     * @param title Title of artwork for which review is given
     * @param reviewText The review
     * @param sellerRating Buyers rating of seller
     * @param listingId Listing ID for which review is given
     */
    public Review(int id, String sellerUsername, long dateCreated, String title,
                  String reviewText, int sellerRating, int listingId) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.title = title;
        this.reviewText = reviewText;
        this.sellerRating = sellerRating;
        this.sellerUsername = sellerUsername;
        this.listingId = listingId;
    }

    /**
     * Construct a new Review from a JsonObject
     *
     * @param jo - The JsonObject to load from
     * @throws Exception - If the loading fails
     */
    public Review(JsonObject jo) throws Exception {
        this.id = jo.get("id").getAsInt();
        this.sellerUsername = jo.get("sellerUsername").getAsString();
        this.dateCreated = jo.get("datecreated").getAsLong();
        this.title = jo.get("title").getAsString();
        this.reviewText = jo.get("review").getAsString();
        this.sellerRating = jo.get("rating").getAsInt();
        this.listingId = jo.get("listingId").getAsInt();
    }

    /**
     * Turn the Review into a JsonObject
     *
     * @return The JsonObject
     */
    public JsonObject toJsonObject() {
        JsonObject jo = new JsonObject();
        jo.addProperty("id", this.id);
        jo.addProperty("sellerUsername", this.sellerUsername);
        jo.addProperty("datecreated", this.dateCreated);
        jo.addProperty("title", this.title);
        jo.addProperty("review", this.reviewText);
        jo.addProperty("rating", this.sellerRating);
        jo.addProperty("listingId", this.listingId);

        return jo;
    }

    public int getId() {
        return id;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public String getTitle() {
        return title;
    }

    public String getReviewText() {
        return reviewText;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public int getSellerRating() {
        return sellerRating;
    }

    public int getListingId() {
        return listingId;
    }
}
