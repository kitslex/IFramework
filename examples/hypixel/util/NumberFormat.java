package me.ashydev.iframework.hypixel.util;

import java.util.TreeMap;

public class NumberFormat {
    private static final TreeMap<Double, String> ROMAN_NUMERALS = new TreeMap<>() {
        {
            put(1000D, "M");
            put(900D, "CM");
            put(500D, "D");
            put(400D, "CD");
            put(100D, "C");
            put(90D, "XC");
            put(50D, "L");
            put(40D, "XL");
            put(10D, "X");
            put(9D, "IX");
            put(5D, "V");
            put(4D, "IV");
            put(1D, "I");
        }
    };

    public static String roman(double number) {
        if (number == 0) return "0";
        if (number < 0) return "-" + roman(-number);
        var key = ROMAN_NUMERALS.floorKey(number);
        if (number == key) return ROMAN_NUMERALS.get(number);
        return ROMAN_NUMERALS.get(key) + roman(number - key);
    }
}
