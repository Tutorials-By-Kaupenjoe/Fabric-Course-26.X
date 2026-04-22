package net.kaupenjoe.mccourse.entity;

import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.entity.custom.*;
import net.kaupenjoe.mccourse.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;

public class ModEntities {
    public static final ResourceKey<EntityType<?>> GIRAFFE_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "giraffe"));
    public static final ResourceKey<EntityType<?>> CHAIR_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "chair"));
    public static final ResourceKey<EntityType<?>> WARTURTLE_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "warturtle"));
    public static final ResourceKey<EntityType<?>> DODO_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "dodo"));
    public static final ResourceKey<EntityType<?>> BLOODWOOD_BOAT_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "bloodwood_boat"));
    public static final ResourceKey<EntityType<?>> BLOODWOOD_CHEST_BOAT_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "bloodwood_chest_boat"));
    public static final ResourceKey<EntityType<?>> TOMAHAWK_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "tomahawk"));


    public static final EntityType<GiraffeEntity> GIRAFFE = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "giraffe"),
            EntityType.Builder.of(GiraffeEntity::new, MobCategory.CREATURE).sized(1.5f, 6.5f)
                    .eyeHeight(6.5f).build(GIRAFFE_KEY));

    public static final EntityType<ChairEntity> CHAIR = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "chair"),
            EntityType.Builder.of(ChairEntity::new, MobCategory.MISC).noLootTable()
                    .sized(0.5f, 0.5f).build(CHAIR_KEY));


    public static final EntityType<WarturtleEntity> WARTURTLE = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "warturtle"),
            EntityType.Builder.of(WarturtleEntity::new, MobCategory.CREATURE).sized(2.5f, 1.5f)
                    .build(WARTURTLE_KEY));
    public static final EntityType<DodoEntity> DODO = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "dodo"),
            EntityType.Builder.of(DodoEntity::new, MobCategory.CREATURE).sized(1f, 2.5f)
                    .build(DODO_KEY));

    public static final EntityType<Boat> BLOODWOOD_BOAT = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "bloodwood_boat"),
            EntityType.Builder.<Boat>of((entityType, level) -> new Boat(entityType, level, () -> ModItems.BLOODWOOD_BOAT),
                            MobCategory.MISC).sized(1.375f, 0.5625f)
                    .clientTrackingRange(10).build(BLOODWOOD_BOAT_KEY));
    public static final EntityType<ChestBoat> BLOODWOOD_CHEST_BOAT = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "bloodwood_chest_boat"),
            EntityType.Builder.<ChestBoat>of((entityType, level) -> new ChestBoat(entityType, level, () -> ModItems.BLOODWOOD_CHEST_BOAT),
                            MobCategory.MISC).sized(1.375f, 0.5625f)
                    .clientTrackingRange(10).build(BLOODWOOD_CHEST_BOAT_KEY));

    public static final EntityType<TomahawkProjectileEntity> TOMAHAWK = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "tomahawk"),
            EntityType.Builder.<TomahawkProjectileEntity>of(TomahawkProjectileEntity::new, MobCategory.MISC).noLootTable()
                    .sized(0.5f, 1.15f).build(TOMAHAWK_KEY));




    public static void registerModEntities() {
        MCCourse.LOGGER.info("Registering Mod Entities for " + MCCourse.MOD_ID);
    }
}
