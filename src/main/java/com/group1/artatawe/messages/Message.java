package com.group1.artatawe.messages;

/**
 * @author Oskar Figura (915070)
 * Represents a single message in a conversation
 */
public class Message {
    private final long date;
    private final String author;
    private final String message;

    public Message(long date, String author, String message) {
        this.date = date;
        this.author = author;
        this.message = message;
    }

    public long getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }
}
