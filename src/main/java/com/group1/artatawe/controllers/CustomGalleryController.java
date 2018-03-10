package com.group1.artatawe.controllers;

import com.group1.artatawe.Main;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.LinkedList;
import java.util.List;

public class CustomGalleryController {
    //Header Attributes
    @FXML StackPane topstack;
    @FXML ImageView profileimage;

    @FXML Button home;
    @FXML Button currentlistings;
    @FXML Button createlisting;
    @FXML Button logout;
    @FXML Button buttonMyGalleries;

    @FXML TilePane tilePaneGalleries;
    @FXML VBox vboxOptions;

    private List<RadioButton> buttons = new LinkedList<RadioButton>();

    public void initialize() {

        this.initializeHeader();
        this.fixTile();
        this.anyGalleries();

        }

    /**
     * Initializes all of the header elements such as linking buttons
     */
    private void initializeHeader() {
        this.currentlistings.setOnMouseClicked(e -> Main.switchScene("CurrentListings"));
        this.profileimage.setImage((Main.accountManager.getLoggedIn().getAvatar()));
        this.createlisting.setOnMouseClicked(e -> Main.switchScene("CreateListing"));
        this.home.setOnMouseClicked(e -> Main.switchScene("Home"));
        this.logout.setOnMouseClicked(e -> Main.accountManager.logoutCurrentAccount());

        this.topstack.setOnMouseClicked(e -> {
            if(this.profileimage.intersects(e.getX(), e.getY(), 0, 0)) {
                ProfileController.viewProfile(Main.accountManager.getLoggedIn());
            }
        });
    }

    /**
     * Checks if the user has any galleries, if not a notification is sent
     */
    private void anyGalleries() {

        boolean noListings = Main.accountManager.getLoggedIn().getUserGalleries()
                .stream()
                .allMatch(x -> x.getListings().isEmpty());

        int size = Main.accountManager.getLoggedIn().getUserGalleries().size(); // all galleries

        if (size == 0) {
            
            noGalleries();

        } else if (noListings) {

            noListings();

        } else {

            this.renderGalleries();
            this.renderButtons(size, Main.accountManager.getLoggedIn().getGalleryNames());

        }
    }

    /**
     *
     * @param size
     */
    private void renderButtons(int size, List<String> names) {

        Label l = new Label("Select from the display options");
        l.setFont(new Font("Calibri", 18));
        l.setPadding(new Insets(15.0, 15.0, 0.0, 15.0));

        RadioButton rb = new RadioButton("All galleries");
        rb.setPadding(new Insets(15.0, 15.0, 0.0, 15.0));

        buttons.add(rb);
        vboxOptions.getChildren().addAll(l, rb);

        while (size > 0) {

            String name = names.get((names.size() - 1) - (size - 1));
            RadioButton r = new RadioButton(name);
            r.setPadding(new Insets(15.0, 15.0, 0.0, 15.0));

            buttons.add(r);
            vboxOptions.getChildren().add(r);
            size--;
        }
    }

    /**
     * Renders all artworks/listings from all galleries a user has
     */
    private void renderGalleries() {
        Main.accountManager.getLoggedIn().getUserGalleries().stream().forEach(gallery -> {
            gallery.getListings().stream().forEach(listing -> {
                String title = gallery.getName();
                Image image = listing.getArtwork().getImage();
                ImageView iv = makeImgView(image);
                VBox v = galleryNode(iv, title);
                v.setOnMouseClicked(e -> ViewListingController.viewListing(listing));
                this.tilePaneGalleries.getChildren().add(v);
            });
        });

    }

    /**
     * Creates a VBox container
     * @param iv
     * @param title
     * @return
     */
    private VBox galleryNode(ImageView iv, String title) {

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(150.0, 150.0);

        Label label = new Label(title);
        vbox.getChildren().addAll(label, iv);

        return vbox;
    }

    /**
     * A method to notify the user that currently he does not have any custom galleries
     */
    private void noGalleries() {

        Label l = new Label("You do not have any galleries");
        l.setFont(new Font("Calibri", 24));

        tilePaneGalleries.setAlignment(Pos.CENTER);
        tilePaneGalleries.getChildren().add(l);

    }

    private void noListings() {
        Label l = new Label("You do not have any listings in your galleries, click to add.");
        l.setFont(new Font("Calibri", 24));
        l.setOnMouseClicked(e -> Main.switchScene("CurrentListings"));

        tilePaneGalleries.setAlignment(Pos.CENTER);
        tilePaneGalleries.getChildren().add(l);

    }

    /**
     * Makes an image view which would be added to a VBox container holding a listing from a particular gallery
     * @param image
     * @return
     */
    private ImageView makeImgView(Image image) {

        ImageView im = new ImageView();

        im.setFitHeight(126.0);
        im.setFitWidth(126.0);
        im.setPreserveRatio(false);
        im.setImage(image);

        return im;

    }

    /**
     * Fixes the HGap and padding of the elements inside the TilePane making it a lot easier for the eye
     */
    private void fixTile() {

        tilePaneGalleries.setHgap(10.0);
        tilePaneGalleries.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));

    }
}
