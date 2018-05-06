package com.group1.artatawe.managers;

import com.group1.artatawe.Main;
import com.group1.artatawe.messages.Conversation;

import java.util.LinkedList;

public class MessageManager {
    private static final String MESSAGE_FILE = "messages.json";
    private final LinkedList<Conversation> messages = new LinkedList<>();


    /**
     * TODO
     *
     * LOAD all messages for a logged-in user into the inbox section
     *
     *
     * HANDLE click on messages to display them in the message (textArea)
     *      This will also remove the specific unread MSG if exists in accounts.json
     *      This will also display recipient in the TextArea plus it will disable it
     *
     * HANDLE SEND
     *      Adds new unread message for the recipient in accounts.json
     *
     *
     * HANDLE NEW MSG BTN CLICK
     *      Enables recipient text area
     *
     */


    /**
     * Check if the users exists
     * @param username Username searched for
     * @return True if users exists else false
     */
    private boolean checkIfUserExists(String username) {
        return Main.accountManager.getAccounts().stream()
                .anyMatch(x -> x.getUserName().equals(username));
    }
}
