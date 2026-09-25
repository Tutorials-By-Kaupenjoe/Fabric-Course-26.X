package net.kaupenjoe.mccourse;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.kaupenjoe.mccourse.attachment.ModAttachmentTypes;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.block.entity.ModBlockEntities;
import net.kaupenjoe.mccourse.consumeeffect.ModConsumeEffects;
import net.kaupenjoe.mccourse.data.ModDataComponents;
import net.kaupenjoe.mccourse.effect.ModEffects;
import net.kaupenjoe.mccourse.enchantment.ModEnchantmentEffects;
import net.kaupenjoe.mccourse.entity.ModEntities;
import net.kaupenjoe.mccourse.entity.custom.DodoEntity;
import net.kaupenjoe.mccourse.entity.custom.GiraffeEntity;
import net.kaupenjoe.mccourse.entity.custom.WarturtleEntity;
import net.kaupenjoe.mccourse.event.ModAttackEntityHandler;
import net.kaupenjoe.mccourse.event.ModServerEvents;
import net.kaupenjoe.mccourse.fluid.ModFluids;
import net.kaupenjoe.mccourse.item.ModCreativeModeTabs;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.loot.ModLootTableModifiers;
import net.kaupenjoe.mccourse.menu.ModMenuTypes;
import net.kaupenjoe.mccourse.networking.ModPackets;
import net.kaupenjoe.mccourse.particle.ModParticles;
import net.kaupenjoe.mccourse.potion.ModPotions;
import net.kaupenjoe.mccourse.recipe.ModRecipes;
import net.kaupenjoe.mccourse.registries.ModCommands;
import net.kaupenjoe.mccourse.registries.ModFlammableBlocks;
import net.kaupenjoe.mccourse.sound.ModSounds;
import net.kaupenjoe.mccourse.stat.ModStats;
import net.kaupenjoe.mccourse.villager.ModVillagers;
import net.kaupenjoe.mccourse.world.biome.ModBiomes;
import net.kaupenjoe.mccourse.world.biome.ModMaterialRules;
import net.kaupenjoe.mccourse.world.gen.ModWorldGeneration;
import net.kaupenjoe.mccourse.world.tree.ModFoliagePlacerTypes;
import net.kaupenjoe.mccourse.world.tree.ModTrunkPlacerTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import terrablender.api.MaterialRuleManager;
import terrablender.api.TerraBlenderApi;

// An extremely important comment
public class MCCourse implements ModInitializer, TerraBlenderApi {
	public static final String MOD_ID = "mccourse";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerCreativeModeTabs();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModDataComponents.registerDataComponents();
		ModPackets.registerPackets();

		ModAttachmentTypes.registerModAttachments();
		ModConsumeEffects.registerModConsumeEffects();

		ModSounds.registerSounds();
		ModEffects.registerEffects();

		ModPotions.registerPotions();
		ModVillagers.registerVillagers();

		ModParticles.registerParticles();
		ModEnchantmentEffects.registerEnchantmentEffects();

		ModStats.registerStats();
		ModFluids.registerFluids();

		ModWorldGeneration.generateModWorldGen();
		ModTrunkPlacerTypes.registerModTrunkPlacerTypes();

		ModFoliagePlacerTypes.registerModFoliagePlacerTypes();
		ModEntities.registerModEntities();

		ModMenuTypes.registerModMenuTypes();
		ModBlockEntities.registerBlockEntities();

		ModRecipes.registerModRecipes();



		ModCommands.registerCommands();
		ModFlammableBlocks.registerFlammableBlocks();

		BlockTransformerHelper.registerStripping(ModBlocks.BLOODWOOD_LOG, ModBlocks.STRIPPED_BLOODWOOD_LOG);
		BlockTransformerHelper.registerStripping(ModBlocks.BLOODWOOD_WOOD, ModBlocks.STRIPPED_BLOODWOOD_WOOD);


		ServerPlayerEvents.JOIN.register(ModServerEvents::onPlayerJoin);
		ServerPlayerEvents.COPY_FROM.register(ModServerEvents::onPlayerCopyFrom);
		ServerPlayerEvents.AFTER_RESPAWN.register(ModServerEvents::onPlayerAfterRespawn);

		LootTableEvents.MODIFY.register(ModLootTableModifiers::modifyLootTables);

		AttackEntityCallback.EVENT.register(new ModAttackEntityHandler());

		FabricDefaultAttributeRegistry.register(ModEntities.GIRAFFE, GiraffeEntity.createGiraffeAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.WARTURTLE, WarturtleEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.DODO, DodoEntity.createAttributes());
	}

	@Override
	public void onTerraBlenderInitialized() {
		ModBiomes.registerBiomes();

		MaterialRuleManager.addRules(MaterialRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModMaterialRules::makeKaupenValleyRules);
		MaterialRuleManager.addRules(MaterialRuleManager.RuleCategory.NETHER, MOD_ID, ModMaterialRules::makeGlowstonePlainsRules);
		MaterialRuleManager.addRules(MaterialRuleManager.RuleCategory.END, MOD_ID, ModMaterialRules::makeEndRotRules);
	}
}