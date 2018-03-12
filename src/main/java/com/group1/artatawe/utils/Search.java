package com.group1.artatawe.utils;
import java.util.*;
import java.util.regex.*;

import com.group1.artatawe.Main;
import com.group1.artatawe.artwork.Artwork;
import com.group1.artatawe.listings.Listing;
import com.group1.artatawe.managers.ListingManager;

public class Search {

    public static Set<Listing> searchForDetails(String s) {

        List<Listing> lists = Main.listingManager.getAllActiveListings();

        String pattern = "(.*)(.*" + s + ".*)(.*)";
        Set<Listing> found = new HashSet<Listing>();

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m;

        for (Listing L : lists) {

            m = r.matcher(L.getArtwork().getTitle());
            if (m.find()) {
                found.add(L);
            }

            m = r.matcher(L.getArtwork().getDescription());
            if (m.find()) {
                found.add(L);
            }

            m = r.matcher(L.getArtwork().getArtist());
            if (m.find()) {
                found.add(L);

            }

            String ss = String.valueOf(L.getArtwork().getYear());
            m = r.matcher(ss);
            if (m.find()) {
                found.add(L);
            }

        }

        return found;
    }}
