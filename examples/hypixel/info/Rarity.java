package me.ashydev.iframework.hypixel.info;

import lombok.Getter;

@Getter
public enum Rarity {
    BASIC("Basic", "#5c5c5c", 0),
    COMMON("Common",  "#ffffff", 1),
    UNCOMMON("Uncommon", "#4efc74", 2),
    RARE("Rare", "#5685fc", 3),
    EPIC("Epic", "#944dff", 4),
    LEGENDARY("Legendary", "#f7bd3e", 5),
    MYTHIC("Mythic", "#cf63f2", 6),
    ULTIMATE("Ultimate", "#5cffa3", 7),

    ADMIN("Admin", "#eb504d", 1000),


    ;

    private final String name;
    private final String color;
    private final int id;

    Rarity(String name, String color, int id) {
        this.name = name;
        this.color = "&" + color;
        this.id = id;
    }

    public static String[] getNames() {
        String[] names = new String[Rarity.values().length];
        for (int i = 0; i < names.length; i++) {
            names[i] = Rarity.values()[i].name();
        }
        return names;
    }
}

