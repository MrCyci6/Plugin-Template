package fr.mrcyci6.atouts.utils;

import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.ChatColor;

public class ColoredText {
    public ColoredText() {
    }

    public static String getText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> getText(List<String> messages) {
        return (List)messages.stream().map(ColoredText::getText).collect(Collectors.toList());
    }
}