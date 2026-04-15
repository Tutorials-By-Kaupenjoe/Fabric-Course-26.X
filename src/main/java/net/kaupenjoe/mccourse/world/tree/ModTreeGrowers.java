package net.kaupenjoe.mccourse.world.tree;

import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.world.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower BLOODWOOD = new TreeGrower(MCCourse.MOD_ID + ":bloodwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.BLOODWOOD_TREE_KEY), Optional.empty());
}
