package net.kaupenjoe.mccourse.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.builder.SoundTypeBuilder;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;
import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.sound.ModSounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModSoundsProvider extends FabricSoundsProvider {
    public ModSoundsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registryLookup, SoundExporter exporter) {
        exporter.add(ModSounds.CHISEL_USE, SoundTypeBuilder.of(ModSounds.CHISEL_USE)
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "chisel_use"))));

        exporter.add(ModSounds.MAGIC_BLOCK_BREAK, SoundTypeBuilder.of(ModSounds.MAGIC_BLOCK_BREAK)
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "magic_block_break"))));
        exporter.add(ModSounds.MAGIC_BLOCK_STEP, SoundTypeBuilder.of(ModSounds.MAGIC_BLOCK_STEP)
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "magic_block_step"))));
        exporter.add(ModSounds.MAGIC_BLOCK_PLACE, SoundTypeBuilder.of(ModSounds.MAGIC_BLOCK_PLACE)
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "magic_block_place"))));
        exporter.add(ModSounds.MAGIC_BLOCK_HIT, SoundTypeBuilder.of(ModSounds.MAGIC_BLOCK_HIT)
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "magic_block_hit"))));
        exporter.add(ModSounds.MAGIC_BLOCK_FALL, SoundTypeBuilder.of(ModSounds.MAGIC_BLOCK_FALL)
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "magic_block_fall"))));


    }

    @Override
    public String getName() {
        return "MCCourse Sounds";
    }
}
