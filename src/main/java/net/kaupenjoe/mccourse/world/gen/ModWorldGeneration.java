package net.kaupenjoe.mccourse.world.gen;

public class ModWorldGeneration {
    public static void generateModWorldGen() {
        ModGeodeGeneration.generateGeodes();

        ModOreGeneration.generateOres();

        ModTreeGeneration.generateTrees();
        ModFlowerGeneration.generateFlowers();
        ModBushGeneration.generateBushes();

        ModEntitySpawns.addSpawns();
    }
}
