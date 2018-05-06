package com.group1.artatawe.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.awt.*;

/**
 * @author Oskar Figura (915070)
 */
public class MessageController {

    @FXML
    private Label lblRecipient;
    @FXML
    private TextArea txtUserMsg;
    @FXML
    private Button btnSend;
    @FXML
    private TextArea txtAreaServerMsgs;

    private ObservableList<String> usersMessages;


    /**
     * To start the dialog
     */
    public void start() {
        String msg = "Random message";
        display(msg);
    }

    /**
     * Send a message to the GUI
     * @param msg The message
     */
    private void display(String msg) {
        txtAreaServerMsgs.appendText(msg + "\n"); // append to the Chat Area
    }
}
