package net.kaupenjoe.mccourse.menu.renderer;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import java.util.List;

/*
 *  BluSunrize
 *  Copyright (c) 2021
 *
 *  This code is licensed under "Blu's License of Common Sense"
 *  https://github.com/BluSunrize/ImmersiveEngineering/blob/1.19.2/LICENSE
 *
 *  Modified Version by: Kaupenjoe
 */
public class EnergyDisplayTooltipArea {
    private final int xPos;
    private final int yPos;
    private final int width;
    private final int height;
    private final SimpleEnergyStorage energy;

    public EnergyDisplayTooltipArea(int xMin, int yMin, SimpleEnergyStorage energy) {
        this(xMin, yMin, energy, 8, 64);
    }

    public EnergyDisplayTooltipArea(int xMin, int yMin, SimpleEnergyStorage energy, int width, int height) {
        xPos = xMin;
        yPos = yMin;
        this.width = width;
        this.height = height;
        this.energy = energy;
    }

    public List<Component> getTooltips() {
        return List.of(Component.literal(energy.getAmount() + " / " + energy.getCapacity() + " FE")); // IS FE THE ACTUAL UNIT????
    }

    public void render(GuiGraphicsExtractor guiGraphics) {
        int stored = (int) (height * (energy.getAmount() / (float) energy.getCapacity()));
        guiGraphics.fillGradient(xPos, yPos + (height - stored), xPos + width,
                yPos + height, 0xffb51500, 0xff600b00);
    }
}