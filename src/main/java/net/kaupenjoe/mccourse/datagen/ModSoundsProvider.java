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


    }

    @Override
    public String getName() {
        return "MCCourse Sounds";
    }
}
