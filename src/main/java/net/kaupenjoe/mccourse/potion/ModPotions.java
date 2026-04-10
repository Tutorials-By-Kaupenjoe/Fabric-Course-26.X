package net.kaupenjoe.mccourse.potion;

import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class ModPotions {
    public static final Holder<Potion> STINKY_POTION = registerPotion("stinky_potion",
            new Potion("stinky_potion", new MobEffectInstance(ModEffects.STINKY, 1200, 0)));

    private static Holder<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name), potion);
    }

    public static void registerPotions() {
        MCCourse.LOGGER.info("Registering Potions for " + MCCourse.MOD_ID);
    }
}
