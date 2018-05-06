package com.group1.artatawe.messages;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.group1.artatawe.Main;
import com.group1.artatawe.accounts.Account;

import java.util.Map;

/**
 * Represents a single conversation between two users
 * @author Oskar Figura (915070)
 */
public class Conversation {
    private final int id;
    private final String converser1;
    private final String converser2;
    private final MessageHistory messageHistory;

    /**
     * Construct a new Conversation
     * @param id The ID of conversation
     * @param converser1 First participant in conversation
     * @param converser2 Second participant in conversation
     */
    public Conversation(int id, String converser1, String converser2) {
        this.id = id;
        this.converser1 = converser1;
        this.converser2 = converser2;
        this.messageHistory = new MessageHistory();
    }

    /**
     * Construct a new Conversation from a JsonObject
     *
     * @param jo - The JsonObject to load from
     * @throws Exception - If the loading fails
     */
    public Conversation(JsonObject jo) throws Exception {
        this.id = jo.get("id").getAsInt();
        this.converser1 = jo.get("converser1").getAsString();
        this.converser2 = jo.get("converser2").getAsString();
        this.messageHistory = new MessageHistory(jo);
    }

    /**
     * Create a message for this conversation
     *
     * @param author  - The author of message
     * @param message - The message
     * @param recipient - The account of recipient
     */
    public void createMessage(String author, String message, Account recipient) {
        recipient.addNewUnreadMessage(this.id);
        this.messageHistory.createNewMessage(author, message);
        Main.messageManager.saveMessagesFile();
    }

    /**
     * Get the ID of this conversation
     * @return This conversation's ID
     */
    public int getID() {
        return this.id;
    }

    /**
     * Get first user from conversation
     * @return The first converser
     */
    public String getConverser1() {
        return converser1;
    }

    /**
     * Get second user from conversation
     * @return The second converser
     */
    public String getConverser2() {
        return converser2;
    }

    /**
     * Get the message history
     * @return The MessageHistory of this conversation
     */
    public MessageHistory getMessageHistory() {
        return messageHistory;
    }

    /**
     * Get the current message (from the MessageHistory)
     * @return The current message. Null if no current message
     */
    public Message getCurrentMessage() {
        return this.messageHistory.getCurrentMessage();
    }

    /**
     * Turn the Conversation into a JsonObject
     * @return The JsonObject
     */
    public JsonObject toJsonObject() {
        JsonObject jo = new JsonObject();
        jo.addProperty("id", this.id);
        jo.addProperty("converser1", this.converser1);
        jo.addProperty("converser2", this.converser2);

        for(Map.Entry<String, JsonElement> entry : this.messageHistory.toJsonObject().entrySet()) {
            jo.add(entry.getKey(), entry.getValue());
        }
        return jo;
    }
}
