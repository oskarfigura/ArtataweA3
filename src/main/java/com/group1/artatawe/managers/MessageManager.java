package com.group1.artatawe.managers;

import com.group1.artatawe.messages.Conversation;

import java.util.LinkedList;

public class MessageManager {
    private static final String MESSAGE_FILE = "messages.json";
    private final LinkedList<Conversation> messages = new LinkedList<>();
}
