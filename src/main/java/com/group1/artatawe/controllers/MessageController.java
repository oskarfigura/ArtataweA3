package com.group1.artatawe.controllers;

import com.group1.artatawe.Main;
import com.group1.artatawe.accounts.Account;
import com.group1.artatawe.messages.Conversation;
import com.group1.artatawe.messages.Message;
import com.group1.artatawe.messages.MessageHistory;
import com.group1.artatawe.utils.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;

import static com.group1.artatawe.controllers.ProfileController.viewProfile;

/**
 * Controller for "MsgWindow.fxml", handles message processing
 * @author Oskar Figura (915070)
 */
public class MessageController {

    //Header Attributes
    @FXML
    StackPane topstack;
    @FXML
    ImageView profileimage;

    @FXML
    Button home;
    @FXML
    Button currentlistings;
    @FXML
    Button createlisting;
    @FXML
    Button logout;
    @FXML
    Button buttonMyGalleries;

    //Chat attributes
    @FXML
    TextFlow txtAreaMsgs;
    @FXML
    Button btnSend;
    @FXML
    Button btnNewMessage;
    @FXML
    TextArea txtUserMsg;
    @FXML
    TextField txtRecipient;
    @FXML
    ListView<String> listUser;
    @FXML
    ScrollPane txtMsgAreaScroll;
    @FXML
    Label lblTitle;

    private String loggedUser;
    private String recipient;
    private Account recipientAcc;
    private String message;
    private boolean newMsg;
    private boolean msgSelected;
    private ObservableList<String> userList;

    /**
     * Initializes the system with some basic data ready for user to
     * use the messaging functionality
     */
    public void initialize() {
        this.initializeHeader();
        this.recipient = "";
        this.message = "";
        this.msgSelected = false;
        newMsg = false;
        txtAreaMsgs.setPrefWidth(600);

        loggedUser = Main.accountManager.getLoggedIn().getUserName();
        userList = FXCollections.observableArrayList();
        updateUserList();
    }

    /**
     * Initializes all of the header elements such as linking buttons
     */
    private void initializeHeader() {
        this.currentlistings.setOnMouseClicked(e -> Main.switchScene("CurrentListings"));
        this.profileimage.setImage(Main.accountManager.getLoggedIn().getAvatar());
        this.profileimage.setOnMouseClicked(e -> viewProfile(Main.accountManager.getLoggedIn()));
        this.createlisting.setOnMouseClicked(e -> Main.switchScene("CreateListing"));
        this.home.setOnMouseClicked(e -> Main.switchScene("Home"));
        this.logout.setOnMouseClicked(e -> Main.accountManager.logoutCurrentAccount());
        this.buttonMyGalleries.setOnMouseClicked(e -> Main.switchScene("UserGallery"));

        this.topstack.setOnMouseClicked(e -> {
            if (this.profileimage.intersects(e.getX(), e.getY(), 0, 0)) {
                viewProfile(Main.accountManager.getLoggedIn());
            }
        });
    }

    /**
     * Loads all username with who the logged-in user had a conversation
     */
    public void updateUserList() {
        List<Conversation> conversations = Main.messageManager.getUsersConversations(loggedUser);
        String user;

        userList.removeAll();
        listUser.getItems().removeAll();
        listUser.getItems().clear();

        for (Conversation conversation : conversations) {
            user = conversation.getConverser1().equals(loggedUser) ?
                    conversation.getConverser2() : conversation.getConverser1();
            userList.add(user);
        }
        listUser.setItems(userList);
    }

    /**
     * Detects which username has been clicked from inbox
     */
    public void inboxClicked() {
        Conversation conversation;
        recipient = listUser.getSelectionModel().getSelectedItems().toString();
        if (recipient != null) {
            msgSelected = true;
            recipient = recipient
                    .replaceAll("\\[", "")
                    .replaceAll("\\]", "")
                    .replaceAll("\\*", "");
            conversation = Main.messageManager.getConversation(loggedUser, recipient);
            if (conversation != null) {
                displayMessages(conversation);
            }
        }
    }

    /**
     * Displays messages for a particular conversation
     * @param conversation Conversation for which messages are displayed
     */
    private void displayMessages(Conversation conversation) {
        MessageHistory msgHistory = conversation.getMessageHistory();
        List<Message> msgList = msgHistory.getAllMessages();

        List<Text> chatMessages = new ArrayList<>();
        txtAreaMsgs.getChildren().clear();

        for (Message m : msgList) {
            chatMessages.add(new Text() {{
                setText(m.getAuthor() + " >> " + m.getMessage() + '\n');
                if (m.getAuthor().equals(recipient)) {
                    setFill(Color.RED);
                } else {
                    setFill(Color.BLUE);
                }
            }});
        }

        for (Text t : chatMessages) {
            txtAreaMsgs.getChildren().add(t);
        }

        //The following code is used to refresh TextFlow which would only refresh on mouse click

        //Store current mouse position
        double currentX = MouseInfo.getPointerInfo().getLocation().x;
        double currentY = MouseInfo.getPointerInfo().getLocation().y;

        //Locate the position of TextFlow
        Bounds boundsInScreen = txtAreaMsgs.localToScreen(txtAreaMsgs.getBoundsInLocal());
        double x = boundsInScreen.getMaxX();
        double y = boundsInScreen.getMinY();

        //Fire a mouse click on TextFlow to refresh and return to original position
        try{
            Robot robot = new Robot();
            robot.mouseMove((int)x, (int)y);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.mouseMove((int)currentX, (int)currentY);
        }catch (AWTException ex){
            ex.printStackTrace();
        }

        //Scroll to the bottom of messages
        txtAreaMsgs.heightProperty().addListener(observable -> txtMsgAreaScroll.setVvalue(1D));
    }

    /**
     * Enables user to send message to new recipient
     */
    public void newMessage() {
        txtRecipient.setDisable(false);
        txtRecipient.setEditable(true);
        newMsg = true;
    }

    /**
     * Validates a message to a new recipient
     * @return true if data valid else false
     */
    private boolean validateNewMsg() {
        boolean state;
        if (recipient.equals("")) {
            AlertUtil.sendAlert(Alert.AlertType.INFORMATION, "Error", "Recipient Missing");
            state = false;
        } else if (Main.accountManager.getAccount(recipient) == null) {
            AlertUtil.sendAlert(
                    Alert.AlertType.INFORMATION, "Error", "Incorrect recipient username");
            state = false;
        } else if (recipient.equals(loggedUser)){
            AlertUtil.sendAlert(
                    Alert.AlertType.INFORMATION, "Error", "You cannot message yourself");
            state = false;
        } else if (message.equals("")) {
            AlertUtil.sendAlert(Alert.AlertType.INFORMATION, "Error", "Message cannot be empty");
            state = false;
        } else {
            state = true;
        }
        return state;
    }

    /**
     * Validates a message
     * @return true if message valid else false
     */
    private boolean validateMsg() {
        if (message.equals("")) {
            AlertUtil.sendAlert(Alert.AlertType.INFORMATION, "Error", "Message cannot be empty");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Adds a new message to the system
     */
    private void handleSend() {
        if (Main.messageManager.getConversation(loggedUser, recipient) != null) {
            Main.messageManager.getConversation(loggedUser, recipient)
                    .createMessage(loggedUser, message, recipientAcc);
        } else {
            Main.messageManager.addConversation(loggedUser, recipient)
                    .createMessage(loggedUser, message, recipientAcc);
            updateUserList();
        }
        txtRecipient.setDisable(true);
        txtRecipient.setEditable(false);
        txtRecipient.clear();
        txtUserMsg.clear();
        newMsg = false;
        txtAreaMsgs.getChildren().clear();

        //Refresh chat with new message
        Conversation conversation = Main.messageManager.getConversation(loggedUser, recipient);
        if (conversation != null) {
            displayMessages(conversation);
        }
    }

    /**
     * Processes recipient and message data before it is sent to handleSend()
     */
    public void sendMessage() {
        if (!newMsg && !msgSelected) {
            AlertUtil.sendAlert(Alert.AlertType.INFORMATION, "Error",
                    "Select a conversation or create a new message.");
        } else {
            message = txtUserMsg.getText();

            if (newMsg) {
                recipient = txtRecipient.getText();
                if(validateNewMsg()) {
                    recipientAcc = Main.accountManager.getAccount(recipient);
                    handleSend();
                }
            } else if (!newMsg && validateMsg() && msgSelected) {
                handleSend();
            }
        }
    }
}
