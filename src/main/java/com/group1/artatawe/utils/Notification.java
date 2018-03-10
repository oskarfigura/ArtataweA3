package com.group1.artatawe.utils;

import com.group1.artatawe.Main;
import com.group1.artatawe.accounts.Account;
import com.group1.artatawe.listings.Listing;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class used to control the creation of notifications.
 *
 * @author Oskar Figura and Nikolina Antoniou
 * @version 1.0
 * created on: 05/03/2018.
 */
/*
TODO:
1. New auctions notification. (DONE???)
2. Seller can see new bids on the items.
3. Auctions lost since last login.
4. Auctions user bid on, which are approaching their bid limit. (DONE???)
 */
public class Notification {
    public Notification() {

    }

    /**
     * Get a List of new artworks that are now on auction since last login
     *
     * @return List of new artworks since last login
     */
    public List<Listing> getNewListings() {
        long lastLoginDate = Main.accountManager.getLoggedIn().getLastLogin();
        return Main.listingManager.getAllActiveListings()
                .stream().filter(x -> x.getDateCreated() > lastLoginDate)
                .collect(Collectors.toList());
    }

    /**
     * Get a list of new bids on sellers auctions since last login
     * @return List of listings with new bids on sellers auctions
     */
    public List<Listing> getNewBids() {
        String currentUser = Main.accountManager.getLoggedIn().getUserName();
        long lastLoginDate = Main.accountManager.getLoggedIn().getLastLogin();

        return Main.listingManager.getAllActiveListings().stream()
                .filter(x -> x.getSeller().equals(currentUser))
                .filter(x -> x.getNumOfBids() > 0)
                .filter(x -> x.getCurrentBid().getDate() > lastLoginDate)
                .collect(Collectors.toList());
    }

    /**
     * List of auctions a user bid on that are coming to a close
     * so they have less than 2 bids left
     *
     * @return List of auctions close to their bid limit
     */
    public List<Listing> getEndingListings() {
        Account currentUser = Main.accountManager.getLoggedIn();
        return Main.listingManager.getAllActiveListings()
                .stream().filter(x -> x.getBidsLeft() < 5)
                .filter(a -> a.getBidHistory().getBid(currentUser) != null)
                .collect(Collectors.toList());
    }


//    /**
//     * Get a list of lost auctions since last login
//     * @return List of lost auctions
//     */
//    public List<Listing> getLostListings() {
//        //TODO - In other words get a list of auctions that have ended on which user has bid
//        return listingManager.getAllActiveListings()
//                .stream().filter(x -> x.getCurrentBid().getDate() < lastLoginDate).filter(a -> a.getSeller().equals(currentUser))
//                .collect(Collectors.toList());
//    }

}
