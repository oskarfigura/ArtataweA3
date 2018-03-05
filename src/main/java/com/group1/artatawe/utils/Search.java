package com.group1.artatawe.utils;

import com.group1.artatawe.managers.ListingManager;

public class Search {
    public static void main(String[] args) {

        String search = "Jesus Sculpture";  //users search keyword example for testing
        ListingManager listingManager = new ListingManager();
        listingManager.getAllActiveListings();
    }

}
