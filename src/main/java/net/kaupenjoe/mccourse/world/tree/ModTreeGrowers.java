package net.kaupenjoe.mccourse.world.tree;

import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.world.ModFeatures;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower BLOODWOOD = new TreeGrower(MCCourse.MOD_ID + ":bloodwood",
            WeightedList.of(ModFeatures.BLOODWOOD_TREE_KEY), WeightedList.of(), WeightedList.of(), null);
}
