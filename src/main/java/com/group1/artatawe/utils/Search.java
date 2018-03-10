package com.group1.artatawe.utils;
import java.util.regex.*;
import java.util.List;
import com.group1.artatawe.artwork.Artwork;
import com.group1.artatawe.listings.Listing;
import java.util.ArrayList;
import com.group1.artatawe.managers.ListingManager;

public class Search {

    public static ArrayList<Artwork> searchForDetails(String s){

        ListingManager listingManager = new ListingManager();
       // listingManager.getAllActiveListings().stream().filter(x-> x.getArtwork().getDescription().contains("jesus"));
        //listingManager.getAllActiveListings().stream().filter(x-> x.getArtwork().getTitle().contains("jesus"));
        List<Listing> lists = listingManager.getAllActiveListings();

        String pattern = "(.*)(.*" +s +".*)(.*)";
        ArrayList<Artwork> found = new ArrayList<>();
        // Create a Pattern object
        Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m;
        for (Listing L:lists) {
            m = r.matcher(L.getArtwork().getTitle());
            if(m.find()) found.add(L.getArtwork());
            m = r.matcher(L.getArtwork().getDescription());
            if(m.find())  found.add(L.getArtwork());
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
*/      return found;

    }
    public static void main(String[] args) {
        String line = "jesus sculpture";
        ArrayList<Artwork> art1 = searchForDetails("adam");
        ArrayList<Artwork> art2 =  searchForDetails("jESus");
        for(Artwork a:art1)
            System.out.println(a.getTitle());

        for(Artwork a:art2)
            System.out.println(a.getTitle());

    }

}
