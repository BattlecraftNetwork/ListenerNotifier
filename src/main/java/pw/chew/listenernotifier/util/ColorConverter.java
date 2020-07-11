package pw.chew.listenernotifier.util;

import org.bukkit.ChatColor;

public class ColorConverter {
    public static String convertColorCodesToChatColor(String input) {
        String[] colorchars = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "l", "m", "n", "o", "r"};
        String[] bruh = input.split("&");
        StringBuilder output = new StringBuilder();
        for(String moment : bruh) {
            for(String code : colorchars) {
                if(moment.startsWith(code)) {
                    moment = ChatColor.getByChar(code) + moment.substring(1);
                }
            }
            output.append(moment);
        }
        return output.toString();
    }
}
