package net.kaupenjoe.mccourse.enchantment;

import com.mojang.serialization.MapCodec;
import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.enchantment.custom.LightningStrikerEnchantmentEffect;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

public class ModEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> LIGHTNING_STRIKER =
            registerEntityEffect("lightning_striker", LightningStrikerEnchantmentEffect.CODEC);


    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name,
                                                                                    MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {
        MCCourse.LOGGER.info("Registering Enchantment Effects for " + MCCourse.MOD_ID);
    }
}
