package com.group1.artatawe.controllers;

import com.group1.artatawe.Main;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.LinkedList;
import java.util.List;
import java.util.RandomAccess;

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
            this.includeOptions();
            this.renderGalleries();
        }

    private void initializeHeader() {
        this.currentlistings.setOnMouseClicked(e -> Main.switchScene("CurrentListings"));
        this.profileimage.setImage((Main.accountManager.getLoggedIn().getAvatar()));
        this.createlisting.setOnMouseClicked(e -> Main.switchScene("CreateListing"));

        this.logout.setOnMouseClicked(e -> Main.accountManager.logoutCurrentAccount());

        this.topstack.setOnMouseClicked(e -> {
            if(this.profileimage.intersects(e.getX(), e.getY(), 0, 0)) {
                ProfileController.viewProfile(Main.accountManager.getLoggedIn());
            }
        });
    }
    private void includeOptions() {
        int size = Main.accountManager.getLoggedIn().getUserGalleries().size(); // all galleries
        if (size == 0) {
            Label l = new Label("You do not have any galleries");
            l.setFont(new Font("Calibri", 24));
            tilePaneGalleries.setAlignment(Pos.CENTER);
            tilePaneGalleries.getChildren().add(l);
        }

        //TODO -> check the size of the user galleries, if there are 0 display a message saying the user has to create a gallery
        makeRadioButton(size);

        //TODO -> get the names of all galleries
        //TODO -> make a radio button for each + a default one selecting all galleries


    }

    private void makeRadioButton(int size) {
        if (size == 0) {
            Label l = new Label("You do not have any galleries");
            l.setFont(new Font("Calibri", 24));
        } else {
        }
    }
    private void renderGalleries() {

    }


}
