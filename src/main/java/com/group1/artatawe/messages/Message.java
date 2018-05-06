package com.group1.artatawe.messages;

/**
 * Represents a single message in a conversation
 * @author Oskar Figura (915070)
 */
public class Message {
    private final long date;
    private final String author;
    private final String message;

    /**
     * Constructs a new message
     * @param date Date of message
     * @param author Author of message
     * @param message The message
     */
    public Message(long date, String author, String message) {
        this.date = date;
        this.author = author;
        this.message = message;
    }

    /**
     * Get date of message
     * @return date
     */
    public long getDate() {
        return date;
    }

    /**
     * Get the author of message
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get the message
     * @return message
     */
    public String getMessage() {
        return message;
    }
}
