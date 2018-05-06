package com.group1.artatawe.managers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.group1.artatawe.Main;
import com.group1.artatawe.messages.Conversation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

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
     * @throws NoSuchElementException If conversation not found throw exception
     */
    public Conversation getConversation(int id) throws NoSuchElementException {
        return this.conversations.stream()
                .filter(x -> x.getID() == id).findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Conversation id: " + id + " doesn't exist"));
    }

    /**
     * Get the conversation between two specific users
     *
     * @param user1 First user in conversation
     * @param user2 Second user in conversation
     * @return Conversation between users else null
     */
    public Conversation getConversation(String user1, String user2) {
        return this.conversations.stream()
                .filter(x -> x.getConverser1().equals(user1) && x.getConverser2().equals(user2) ||
                        x.getConverser1().equals(user2) && x.getConverser2().equals(user1))
                .findFirst().orElse(null);
    }

    /**
     * Gets a list of users conversations
     * @param username The user for which list is returned
     * @return List of conversations
     */
    public List<Conversation> getUsersConversations(String username) {
        return this.conversations.stream()
                .filter(x -> x.getConverser1().equals(username) ||
                        x.getConverser2().equals(username))
                .collect(Collectors.toList());
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
