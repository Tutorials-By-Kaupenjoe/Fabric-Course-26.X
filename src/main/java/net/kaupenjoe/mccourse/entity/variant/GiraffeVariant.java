package net.kaupenjoe.mccourse.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum GiraffeVariant {
    SPOTTED(0),
    BLANK(1);

    private static final GiraffeVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(
            GiraffeVariant::getId)).toArray(GiraffeVariant[]::new);
    private final int id;

    GiraffeVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static GiraffeVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
