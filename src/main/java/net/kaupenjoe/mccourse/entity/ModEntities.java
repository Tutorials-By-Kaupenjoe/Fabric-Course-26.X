package net.kaupenjoe.mccourse.entity;

import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.entity.custom.GiraffeEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final ResourceKey<EntityType<?>> GIRAFFE_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "giraffe"));


    public static final EntityType<GiraffeEntity> GIRAFFE = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "giraffe"),
            EntityType.Builder.of(GiraffeEntity::new, MobCategory.CREATURE).sized(1.5f, 6.5f)
                    .eyeHeight(6.5f).build(GIRAFFE_KEY));



    public static void registerModEntities() {
        MCCourse.LOGGER.info("Registering Mod Entities for " + MCCourse.MOD_ID);
    }
}
