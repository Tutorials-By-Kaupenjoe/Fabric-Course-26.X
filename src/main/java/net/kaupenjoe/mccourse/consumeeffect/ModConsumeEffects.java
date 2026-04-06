package net.kaupenjoe.mccourse.consumeeffect;

import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.consume_effects.ConsumeEffect;

public class ModConsumeEffects {
    public static final ConsumeEffect.Type<ManaAddConsumeEffect> MANA_ADD_CONSUME_EFFECT = Registry.register(BuiltInRegistries.CONSUME_EFFECT_TYPE,
            Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "mana_add"),
            new ConsumeEffect.Type<>(ManaAddConsumeEffect.CODEC, ManaAddConsumeEffect.STREAM_CODEC));


    public static void registerModConsumeEffects() {
        MCCourse.LOGGER.info("Registering Mod Consume Effects for " + MCCourse.MOD_ID);
    }
}
