package net.kaupenjoe.mccourse.item;

import com.google.common.collect.Maps;
import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.tag.ModTags;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.Map;

public class ModArmorMaterials {
    static ResourceKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = ResourceKey.createRegistryKey(Identifier.parse("equipment_asset"));
    public static final ResourceKey<EquipmentAsset> BISMUTH_KEY = ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "bismuth"));

    public static final ArmorMaterial BISMUTH_ARMOR_MATERIAL = new ArmorMaterial(28,
            makeDefense(2, 4, 6, 2, 5), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 0, 0, ModTags.Items.BISMUTH_REPAIRABLE, BISMUTH_KEY);


    static Map<ArmorType, Integer> makeDefense(final int boots, final int legs, final int chest, final int helm, final int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body));
    }
}
