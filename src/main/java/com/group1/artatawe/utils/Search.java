package com.group1.artatawe.utils;
import java.util.LinkedList;
import java.util.regex.*;
import java.util.List;

import com.group1.artatawe.Main;
import com.group1.artatawe.artwork.Artwork;
import com.group1.artatawe.listings.Listing;
import java.util.ArrayList;
import com.group1.artatawe.managers.ListingManager;

public class Search {

    public static List<Listing> searchForDetails(String s) {

        //ListingManager listingManager = new ListingManager();

        // listingManager.getAllActiveListings().stream().filter(x-> x.getArtwork().getDescription().contains("jesus"));
        //listingManager.getAllActiveListings().stream().filter(x-> x.getArtwork().getTitle().contains("jesus"));
        List<Listing> lists = Main.listingManager.getAllActiveListings();

        String pattern = "(.*)(.*" + s + ".*)(.*)";
        List<Listing> found = new LinkedList<>();
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
                if (!lists.contains(L)) {
                    found.add(L);
                }
            }

            m = r.matcher(L.getArtwork().getArtist());
            if (m.find()) {
                if (!lists.contains(L)) {
                    found.add(L);
                }

            }

            String ss = String.valueOf(L.getArtwork().getYear());
            m = r.matcher(ss);
            if (m.find()) {
                if (!lists.contains(L)) {
                    found.add(L);
                }

            }


            // Now create matcher object.
      /*
        if (m.find( )) {
            return ("Found value: " + m.group(0) );
            //System.out.println("Found value: " + m.group(1) );
            //System.out.println("Found value: " + m.group(2) );
        } else {
            return ("NO MATCH");
        }
*/


        }
        return found;
    }}
