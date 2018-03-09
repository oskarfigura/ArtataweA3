package com.group1.artatawe.controllers;

import com.group1.artatawe.Main;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
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

        int size = Main.accountManager.getLoggedIn().getUserGalleries().size(); // all galleries

        if (size == 0) {
            noGalleries();
        } else {
            this.renderGalleries();
        }
        //TODO -> check the size of the user galleries, if there are 0 display a message saying the user has to create a gallery
        //TODO -> make a radio button for each + a default one selecting all galleries
    }

    /**
     *
     * @param size
     */
    private void makeRadioButton(int size) {

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

        //TODO -> add a button to add a new gallery
    }

    /**
     * A method to notify the user that currently he does not have any artworks/listings in his galleries
     */
    private void NoListings() {

        Label l = new Label("You do not have any listings ");

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
