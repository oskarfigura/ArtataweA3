package com.group1.artatawe.managers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.group1.artatawe.Main;
import com.group1.artatawe.listings.Listing;
import com.group1.artatawe.messages.Conversation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MessageManager {
    private static final String MESSAGE_FILE = "conversations.json";
    private final List<Conversation> conversations = new ArrayList<>();

    /**
     * Construct a new Message Manager
     * Will load all the Messages out of the file
     */
    public MessageManager() {
        this.loadConversations();
    }

    /**
     * Add a conversation to the system
     *
     * @param converser1 - First converser from the conversation
     * @param converser2 - Second converser from the conversation
     * @return The conversation created
     */
    public Conversation addConversation(String converser1, String converser2) {
        int id = conversations.size() + 1;

        Conversation c = new Conversation(id, converser1, converser2);
        this.conversations.add(c);
        this.saveMessagesFile();
        return c;
    }

    /**
     * Get all the conversations in the system
     *
     * @return All conversations
     */
    public List<Conversation> getAllConversations() {
        return this.conversations;
    }

    /**
     * Get conversation with a specific ID
     *
     * @param id The ID to search for
     * @return The conversation with that ID
     * @throws NoSuchElementException
     */
    public Conversation getConversation(int id) throws NoSuchElementException {
        return this.conversations.stream()
                .filter(x -> x.getID() == id).findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Conversation id: " + id + " doesn't exist"));
    }

    /**
     * Save all the messages back to the file
     */
    public void saveMessagesFile() {
        StringBuilder data = new StringBuilder();

        this.conversations.forEach(x -> data.append(x.toJsonObject().toString() + "\n"));
        File messagesFile = new File(MESSAGE_FILE);

        try {
            Files.write(messagesFile.toPath(), data.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open file and load all the conversations out of the file
     */
    public void loadConversations() {
        File file = new File(MESSAGE_FILE);
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine().trim();

                if (!nextLine.isEmpty()) {
                    this.loadConversation(nextLine);
                }
            }
        } catch (FileNotFoundException e) {
            try {
                new File(MESSAGE_FILE).createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }


    private void loadConversation(String jsonString) {
        JsonParser jp = new JsonParser();
        try {
            JsonObject jo = jp.parse(jsonString).getAsJsonObject();

            Conversation conversation = new Conversation(jo);
            this.conversations.add(conversation);
        } catch (Exception e) {
            System.out.println("Parse error on string: \n"
                    + jsonString
                    + "\nThe conversation has not been loaded.");
            System.out.println(e.getMessage());
        }
    }

    /**
     * TODO
     *
     * LOAD all conversations for a logged-in user into the inbox section
     *
     *
     * HANDLE click on conversations to display them in the message (textArea)
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
     *
     * @param username Username searched for
     * @return True if users exists else false
     */
    private boolean checkIfUserExists(String username) {
        return Main.accountManager.getAccounts().stream()
                .anyMatch(x -> x.getUserName().equals(username));
    }
}
