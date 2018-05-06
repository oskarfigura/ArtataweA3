package com.group1.artatawe.messages;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Oskar Figura (915070)
 * Stores messages sent within a conversation
 */
public class MessageHistory {
    private final List<Message> msgHistory = new ArrayList<>();

    /**
     * Construct a new message history
     */
    protected MessageHistory() {
    }

    /**
     * Construct a new message history
     *
     * @param jo - The JsonObject to load from
     */
    public MessageHistory(JsonObject jo) {
        this.loadFromJson(jo);
    }

    /**
     * Get the current (last) message
     *
     * @return The current message. Null if no messages has been sent
     */
    public Message getCurrentMessage() {
        if (this.msgHistory.isEmpty()) {
            return null;
        }
        return this.msgHistory.get(msgHistory.size() - 1);
    }

    /**
     * Get the entire history of messages
     *
     * @return List containing all the messages
     */
    public List<Message> getAllMessages() {
        return this.msgHistory;
    }

    /**
     * Create a new message
     *
     * @param author  The author of message
     * @param message The message sent
     */
    protected void createNewMessage(String author, String message) {
        this.msgHistory.add(new Message(System.currentTimeMillis(), author, message));
    }

    /**
     * Turn the message history into a JsonObject
     *
     * @return The JsonObject
     */
    protected JsonObject toJsonObject() {
        JsonObject jo = new JsonObject();

        JsonArray messageArray = new JsonArray();

        for (Message message : this.msgHistory) {
            JsonObject msgObj = new JsonObject();

            msgObj.addProperty("date", message.getDate());
            msgObj.addProperty("author", message.getAuthor());
            msgObj.addProperty("message", message.getMessage());
            messageArray.add(msgObj);
        }

        jo.add("messageHistory", messageArray);

        return jo;
    }

    /**
     * Load the message history from a JsonObject
     *
     * @param jo - The JsonObject
     */
    protected void loadFromJson(JsonObject jo) {
        JsonArray messages = jo.get("messageHistory").getAsJsonArray();
        Iterator<JsonElement> it = messages.iterator();

        while (it.hasNext()) {
            JsonObject msgObj = it.next().getAsJsonObject();

            long date = msgObj.get("date").getAsLong();
            String author = msgObj.get("author").getAsString();
            String message = msgObj.get("message").getAsString();

            Message msg = new Message(date, author, message);
            this.msgHistory.add(msg);
        }
    }
}
