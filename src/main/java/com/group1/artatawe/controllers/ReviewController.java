package com.group1.artatawe.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewController {

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
        //Load the window for creating reviews here????
        //Limit review to 100 chars
        //Add the review to json
    }
}
