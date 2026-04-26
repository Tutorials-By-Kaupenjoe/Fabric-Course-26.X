package net.kaupenjoe.mccourse.menu.renderer;

import com.google.common.base.Preconditions;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.util.CommonColors;
import net.minecraft.world.level.material.Fluids;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

// CREDIT: https://github.com/mezz/JustEnoughItems by mezz
// Under MIT-License: https://github.com/mezz/JustEnoughItems/blob/1.19/LICENSE.txt
// Includes major rewrites and methods from:
// https://github.com/mezz/JustEnoughItems/blob/1.19/Forge/src/main/java/mezz/jei/forge/platform/FluidHelper.java
// Major rewrites from Kaupenjoe
public class FluidTankRenderer {
    private static final NumberFormat nf = NumberFormat.getIntegerInstance();
    private static final int MIN_FLUID_HEIGHT = 1;
    public final long capacityMb;
    private final TooltipMode tooltipMode;
    private final int width;
    private final int height;

    private static final Identifier STILL_FLUID_TEXTURE = Identifier.fromNamespaceAndPath(MCCourse.MOD_ID,
            "textures/gui/still_fluid.png");

    enum TooltipMode {
        SHOW_AMOUNT,
        SHOW_AMOUNT_AND_CAPACITY,
        ITEM_LIST
    }

    public FluidTankRenderer(long capacity, boolean showCapacity, int width, int height) {
        this(capacity, showCapacity ? TooltipMode.SHOW_AMOUNT_AND_CAPACITY : TooltipMode.SHOW_AMOUNT, width, height);
    }

    private FluidTankRenderer(long capacity, TooltipMode tooltipMode, int width, int height) {
        Preconditions.checkArgument(capacity > 0, "capacity must be > 0");
        Preconditions.checkArgument(width > 0, "width must be > 0");
        Preconditions.checkArgument(height > 0, "height must be > 0");

        this.capacityMb = capacity;
        this.tooltipMode = tooltipMode;
        this.width = width;
        this.height = height;
    }

    public void render(GuiGraphicsExtractor guiGraphics, int x, int y, SingleVariantStorage<FluidVariant> fluidStorage) {
        int scaledAmount = getScaledAmount(fluidStorage);
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, STILL_FLUID_TEXTURE,
                x, y + height - scaledAmount, 0f, 0f, width, scaledAmount, 16, 16, getColorTint(fluidStorage));
    }

    private int getScaledAmount(SingleVariantStorage<FluidVariant> fluidStorage) {
        int amount = Math.toIntExact(fluidStorage.getAmount());
        int scaledAmount = (amount * height) / (int)capacityMb;

        if (amount > 0 && scaledAmount < MIN_FLUID_HEIGHT) {
            scaledAmount = MIN_FLUID_HEIGHT;
        }
        if (scaledAmount > height) {
            scaledAmount = height;
        }
        return scaledAmount;
    }

    private int getColorTint(SingleVariantStorage<FluidVariant> fluidStorage) {
        return fluidStorage.variant.getFluid() == Fluids.WATER ? CommonColors.BLUE :
                fluidStorage.variant.getFluid() == Fluids.LAVA ? CommonColors.RED : CommonColors.WHITE;
    }

    public List<Component> getTooltip(SingleVariantStorage<FluidVariant> fluidStorage) {
        List<Component> tooltip = new ArrayList<>();
        FluidVariant fluidType = fluidStorage.variant;
        if (fluidType.isBlank()) {
            return List.of(Component.literal("Empty"));
        }

        tooltip.add(Component.translatable("block."+ BuiltInRegistries.FLUID.getKey(fluidStorage.variant.getFluid()).getNamespace()
                + "." +BuiltInRegistries.FLUID.getKey(fluidStorage.variant.getFluid()).getPath()));

        long amount = fluidStorage.amount;
        if (tooltipMode == TooltipMode.SHOW_AMOUNT_AND_CAPACITY) {
            MutableComponent amountString = Component.translatable("mccourse.tooltip.liquid.amount.with.capacity", nf.format(amount), nf.format(capacityMb));
            tooltip.add(amountString.withStyle(Style.EMPTY.withColor(CommonColors.DARK_GRAY)));
        } else if (tooltipMode == TooltipMode.SHOW_AMOUNT) {
            MutableComponent amountString = Component.translatable("mccourse.tooltip.liquid.amount", nf.format(amount));
            tooltip.add(amountString.withStyle(Style.EMPTY.withColor(CommonColors.DARK_GRAY)));
        }

        return tooltip;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}