package me.ashydev.iframework.hypixel.info;

import lombok.Getter;

@Getter
public enum Reforge {
    FABLED("Fabled", 0),
    SACRED("Sacred", 1),
    ;

    private final String name;
    private final int id;

    Reforge(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static String[] getNames() {
        String[] names = new String[Reforge.values().length];
        for (int i = 0; i < names.length; i++) {
            names[i] = Reforge.values()[i].name();
        }
        return names;
    }
}
