package net.kaupenjoe.mccourse;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.kaupenjoe.mccourse.attachment.ModAttachmentTypes;
import net.kaupenjoe.mccourse.datagen.*;
import net.kaupenjoe.mccourse.datagen.damage.ModDamageTypes;
import net.kaupenjoe.mccourse.datagen.villager.ModVillagerTradeTags;
import net.kaupenjoe.mccourse.datagen.villager.ModVillagerTrades;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class MCCourseDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModEquipmentAssetProvider::new);
		pack.addProvider(ModSoundsProvider::new);

		pack.addProvider(ModRegistryDataProvider::new);
		pack.addProvider(ModVillagerTradeTags::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.JUKEBOX_SONG, ModJukeboxSongs::bootstrap);
		registryBuilder.add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);
		registryBuilder.add(Registries.VILLAGER_TRADE, ModVillagerTrades::bootstrap);
	}
}
