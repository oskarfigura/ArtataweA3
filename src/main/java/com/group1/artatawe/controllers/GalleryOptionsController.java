package com.group1.artatawe.controllers;

import com.group1.artatawe.Main;
import com.group1.artatawe.artwork.Gallery;
import com.group1.artatawe.utils.AlertUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * @author Kristiyan Vladimirov
 */
public class GalleryOptionsController {

    @FXML ToggleButton toggleDeleteGal;
    @FXML ToggleButton toggleAddGal;
    @FXML Text infoText;
    @FXML TextField textFieldChoice;
    @FXML Button buttonChanges;
    @FXML VBox vboxGalleries;

    private ListView<String> galleries;

    public void initialize() {

        initializeListView();
        initializeStyles();

        toggleAddGal.setOnMouseClicked(e -> this.showAddChanges());
        toggleDeleteGal.setOnMouseClicked(e -> this.showDeleteChanges());
        buttonChanges.setOnMouseClicked(e -> execute());

    }

    /**
     *
     */
    private void initializeStyles() {

        toggleAddGal.setSelected(true);
        toggleAddGal.setStyle("-fx-background-color: #3421a4; -fx-border-color: #3421a4; -fx-text-fill: #fff");
        toggleDeleteGal.setStyle("-fx-background-color: #fff; -fx-border-color: #3421a4; -fx-text-fill: #000");
        infoText.setText("Please name your new Gallery and press add button.");

    }

    /**
     *
     */
    private void initializeListView() {

        this.galleries = new ListView<>();
        this.galleries.setStyle("-fx-border-color: #000");
        vboxGalleries.getChildren().add(galleries);

        this.updateListView();
    }

    /**
     *
     */
    private void updateListView() {
        this.galleries.getItems().clear();
        Main.accountManager.getLoggedIn().getGalleryNames().stream().forEach(x -> {
            this.galleries.getItems().add(x);
        });
        this.addListenersToGalleries();
    }

    /**
     *
     */
    private void addListenersToGalleries() {

        galleries.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            System.out.println("I set the new value");
            textFieldChoice.setText(newValue);
        }));

    }

    /**
     *
     */
    private void showAddChanges() {

        toggleAddGal.setSelected(true);
        toggleAddGal.setStyle("-fx-background-color: #3421a4; -fx-border-color: #3421a4; -fx-text-fill: #fff");
        toggleDeleteGal.setStyle("-fx-background-color: #fff; -fx-border-color: #3421a4; -fx-text-fill: #000");

        infoText.setText("Please name your new Gallery and press add button.");
        buttonChanges.setText("Add gallery");

    }

    /**
     *
     */
    private void showDeleteChanges() {

        toggleDeleteGal.setSelected(true);
        toggleDeleteGal.setStyle("-fx-background-color: #3421a4; -fx-border-color: #3421a4; -fx-text-fill: #fff");
        toggleAddGal.setStyle("-fx-background-color: #fff; -fx-border-color: #3421a4; -fx-text-fill: #000");

        infoText.setText("Please name the gallery you would like to delete, or simple click from the list view.");
        buttonChanges.setText("Delete gallery");

    }

    /**
     *
     */
    private void execute() {

        if (!checkIfEmpty()) {

            if (toggleAddGal.isSelected()) {

                String s = textFieldChoice.getText().trim();
                boolean existence = Main.accountManager.getLoggedIn().checkGallery(s);

                // Checks if a gallery is already there it is not it adds a new one
                if (!existence) {

                    this.galleries.getItems().add(s);
                    Main.accountManager.getLoggedIn().addGallery(new Gallery(Main.accountManager.getLoggedIn(), s));
                    this.addListenersToGalleries();

                    textFieldChoice.clear();

                } else {

                    AlertUtil.sendAlert(Alert.AlertType.ERROR, "Existing gallery",
                            "A gallery with the same name is already present.");
                    galleries.getSelectionModel().clearSelection();

                }

            } else if (toggleDeleteGal.isSelected()){

                String s = textFieldChoice.getText().trim();

                // Performs a check to see if a gallery is present in a user's list of galleries
                // If it is not there false would be returned and thus the user would get notified about this
                // If it is there the user would get notified that the gallery has been deleted
                if (!Main.accountManager.getLoggedIn().removeGallery(s)) {

                    AlertUtil.sendAlert(Alert.AlertType.ERROR, "Gallery does not exist",
                            "Please type in a correct name of a gallery or click from the list view.");

                } else {

                    int selectedIndex = galleries.getSelectionModel().getSelectedIndex();

                    if (selectedIndex != -1) {

                        //int newSelectedIndex = (selectedIndex == galleries.getItems().size() - 1) ? selectedIndex - 1 : selectedIndex;
                        galleries.getItems().remove(selectedIndex);
                        //galleries.getSelectionModel().select(newSelectedIndex);

                    } else {

                        System.out.println(galleries.getItems().remove(textFieldChoice.getText().trim()));

                    }

                    this.updateListView();
                    textFieldChoice.clear();

                }

            }

        } else {

            AlertUtil.sendAlert(Alert.AlertType.ERROR, "Form not filled",
                            "A required field has not been filled!");

        }

        textFieldChoice.clear();

    }

    /**
     *
     * @return
     */
    private boolean checkIfEmpty() {
        if ( textFieldChoice.getText().trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }


}
