package me.ashydev.iframework.hypixel.info;

import lombok.Getter;

@Getter
public enum ItemType {
    PICKAXE("Pickaxe", 0),
    AXE("Axe", 1),
    SHOVEL("Shovel", 2),
    HOE("Hoe", 3),
    SWORD("Sword", 4),
    BOW("Bow", 5),
    ARMOR("Armor", 6),
    BLOCK("Block", 7),
    MISC("Misc", 8),
    ;

    private final String name;

    private final int id;

    ItemType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static String[] getNames() {
        String[] names = new String[ItemType.values().length];
        for (int i = 0; i < names.length; i++) {
            names[i] = ItemType.values()[i].name();
        }
        return names;
    }
}