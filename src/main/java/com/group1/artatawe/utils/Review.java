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

    /**
     * Construct a new Review.
     *
     * @param id
     * @param dateCreated
     * @param title
     * @param reviewText
     * @param sellerRating
     */
    public Review(int id, String sellerUsername, long dateCreated, String title, String reviewText, int sellerRating) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.title = title;
        this.reviewText = reviewText;
        this.sellerRating = sellerRating;
        this.sellerUsername = sellerUsername;
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
}
