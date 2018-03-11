package com.group1.artatawe.managers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.group1.artatawe.artwork.Gallery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Kristiyan Vladimirov
 * made on 06.03.2018
 */
public class GalleryManager {

    private List<Gallery> galleries = new LinkedList<Gallery>();

    public GalleryManager() {
        loadGallaries();
    }

    /**
     * Gets all user galleries
     * @param currentUser
     * @return
     */
    public List<Gallery> getUserGalleries(String currentUser) {
         return galleries.stream()
                    .filter(x -> x.getUser().getUserName().equals(currentUser))
                    .collect(Collectors.toList());
    }

    public void addGallery(Gallery g) {
        this.galleries.add(g);
    }

    /**
     * Get a particular gallery by its name
     * @param currentUser
     * @param name
     * @return
     */
    public Gallery getGalleryByName(String currentUser, String name) {
        return  galleries.stream()
                .filter(x -> x.getUser().getUserName().equals(currentUser) && x.getName().equals(name))
                .findFirst()
                .get();


    }

    public void saveGalleryToFile() {
        //TODO -> sort our the saving of the JSON file
    }

    /**
     * Loads all galleries
     */
    private void loadGallaries() {
        File file = new File("");
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String nextLine = scanner.nextLine().trim();
                if (!nextLine.isEmpty()) {
                    this.loadGallery(nextLine);
                }
            }

        } catch (FileNotFoundException e) {
            /*
            try {
                //TODO -> create a new file if not found
                new File().createNewFile();
            } catch (IOException ee) {
                ee.printStackTrace();
            }
            */
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Load a single gallery
     * @param dataStream
     */
    private void loadGallery(String dataStream) {
        JsonParser jp = new JsonParser();
        try {
            JsonObject jo = jp.parse(dataStream).getAsJsonObject();
            Gallery gallery;
        } catch (JsonParseException e) {
            System.out.println("Parse error, could not load a gallery");
        }
    }
}
