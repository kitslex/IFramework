package me.ashydev.iframework.framework.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;

public class Color {
    private final byte[] bytes = new byte[3];

    public Color(int r, int g, int b) {
        if (r > 255 || r < 0) throw new IllegalArgumentException("r must be between 0 and 255");
        if (g > 255 || g < 0) throw new IllegalArgumentException("g must be between 0 and 255");
        if (b > 255 || b < 0) throw new IllegalArgumentException("b must be between 0 and 255");

        bytes[0] = (byte) r;
        bytes[1] = (byte) g;
        bytes[2] = (byte) b;
    }

    public Color(byte r, byte g, byte b) {
        bytes[0] = r;
        bytes[1] = g;
        bytes[2] = b;
    }

    public Color(String hex) {
        if (hex.startsWith("#")) hex = hex.substring(1);

        byte r = (byte) Integer.parseInt(hex.substring(0, 2), 16);
        byte g = (byte) Integer.parseInt(hex.substring(2, 4), 16);
        byte b = (byte) Integer.parseInt(hex.substring(4, 6), 16);

        bytes[0] = r;
        bytes[1] = g;
        bytes[2] = b;
    }

    public static Color fromHex(String hex) {
        return new Color(hex);
    }

    public static Color fromRGB(int r, int g, int b) {
        return new Color(r, g, b);
    }

    public static Component colorize(String text) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(text).style(style -> style.decoration(TextDecoration.ITALIC, false));
    }

    @SuppressWarnings("deprecation")
    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String getHex(String prefix) {
        return prefix + String.format("%02x%02x%02x", bytes[0], bytes[1], bytes[2]);
    }
}
