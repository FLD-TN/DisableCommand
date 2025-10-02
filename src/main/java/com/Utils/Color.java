package com.Utils; 

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.md_5.bungee.api.ChatColor;

public class Color {

    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
    public static String tran(String msg) {
        Matcher matcher = pattern.matcher(msg);
        while (matcher.find()) {
            String color = msg.substring(matcher.start(), matcher.end());
            msg = msg.replace(color, ChatColor.of(color)+ "");
            matcher = pattern.matcher(msg);
        }
        String msgok = ChatColor.translateAlternateColorCodes('&',msg);
        return msgok.replace("&", "");
    }
}