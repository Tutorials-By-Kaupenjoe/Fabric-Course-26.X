package net.kaupenjoe.mccourse.entity.client;

import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ModModelLayerLocations {
    public static final ModelLayerLocation GIRAFFE =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "giraffe"), "main");

}
