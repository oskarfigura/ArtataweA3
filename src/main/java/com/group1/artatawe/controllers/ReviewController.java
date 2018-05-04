package com.group1.artatawe.controllers;

import com.group1.artatawe.Main;
import com.group1.artatawe.utils.GridUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class ReviewController {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");


    @FXML
    private Label lblDate;
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblTitle;
    @FXML
    private ComboBox ratingList;
    @FXML
    private TextArea txtUserReview;
    @FXML
    private Button btnSaveReview;

    private SimpleDateFormat dateFormat;
    private long currentDate;
    private Date resultdate;

    public void initialize() {
//        this.currentDate = System.currentTimeMillis();
//        this.dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
//        this.resultdate = new Date(currentDate);
//        this.lblDate.setText(dateFormat.format(resultdate));
//        this.lblUsername.setText(Main.accountManager.getLoggedIn().getUserName());
//        this.lblTitle.setText("The title of listing it was clicked from");
//
//        ObservableList<String> ratingOptions =
//                FXCollections.observableArrayList(
//                        "1", "2", "3", "4", "5"
//                );
//        ratingList = new ComboBox(ratingOptions);
        this.renderSellersReviews();
    }

    private void renderSellersReviews(){
        Main.reviewManager.getSellersReviews(Main.accountManager.getLoggedIn());
    }


    /**
     * Render all the Listings the user is currently selling in the box
     */
    private void renderMySelling() {
        LinkedList<Node> nodes = new LinkedList<>();

        Main.accountManager.getLoggedIn().getHistory().getSellingListings().stream()
                .forEach(listing -> nodes.add(this.getSellingListingNode(listing)));

        GridUtil.insertList(this.sellingbox, nodes);
    }


}
