package org.blestit.avaloncore.Modules;

public class ChatColorFix {
    public static String fixColor(String message) {
        return message.replaceAll("&", "§");
    }
}
