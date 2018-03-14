package com.group1.artatawe.utils;
import java.util.*;
import java.util.regex.*;

import com.group1.artatawe.Main;
import com.group1.artatawe.artwork.Artwork;
import com.group1.artatawe.listings.Listing;
import com.group1.artatawe.managers.ListingManager;

public class Search {
    /**
     * Search for method to find pieces with String s in name/description
     * @param s String to be searched for
     * @return Set of liastings including s
     */
    public static Set<Listing> searchForDetails(String s) {

        List<Listing> lists = Main.listingManager.getAllActiveListings();

        String pattern = "(.*)(.*" + s + ".*)(.*)"; //regex pattern to search for words before and after, and s being part of word
        Set<Listing> found = new HashSet<Listing>(); // remove duplicates

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m;

        // finding listings
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
    }
}
