package net.kaupenjoe.mccourse.compat;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import java.util.LinkedList;
import java.util.List;

public class PedestalRecipeCategory implements DisplayCategory<Display> {
    @Override
    public CategoryIdentifier<? extends Display> getCategoryIdentifier() {
        return MCCourseREICommon.PEDESTAL_CRAFTING;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Pedestal");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.MAIN_PEDESTAL.asItem().getDefaultInstance());
    }

    @Override
    public List<Widget> setupDisplay(Display display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 62);
        List<Widget> widgets = new LinkedList<>();

        var ingredients = display.getInputEntries();

        int radius = 45;
        int centerX = 50 + startPoint.x;
        int centerY = 62 + startPoint.y;
        int yOffset = -15;
        int xOffset = 1;

        widgets.add(Widgets.createSlot(new Point(centerX + xOffset, centerY + yOffset))
                .entries(display.getInputEntries().get(0)).markInput());

        int outerItemsCount = ingredients.size() - 1;

        for (int i = 0; i < outerItemsCount; i++) {
            double angle = i * (2 * Math.PI / outerItemsCount);

            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));

            widgets.add(Widgets.createSlot(new Point(x + xOffset, y + yOffset))
                    .entries(display.getInputEntries().get(i + 1)).markInput());
        }
        widgets.add(Widgets.createSlot(new Point(centerX + xOffset + 100, centerY + yOffset))
                .entries(display.getOutputEntries().get(0)).markOutput());

        // Drawing the pedestal
        widgets.add(Widgets.createDrawableWidget((graphics, mouseX, mouseY, delta) -> {
            graphics.fakeItem(new ItemStack(ModBlocks.MAIN_PEDESTAL), centerX, centerY);

            for (int i = 0; i < 8; i++) {
                double angle = i * (Math.PI / 4.0);
                int x = (int) (centerX + radius * Math.cos(angle));
                int y = (int) (centerY + radius * Math.sin(angle));

                graphics.fakeItem(new ItemStack(ModBlocks.SIDE_PEDESTAL), x, y);
            }

            graphics.fakeItem(new ItemStack(ModBlocks.MAIN_PEDESTAL), centerX + 100, centerY);
        }));

        widgets.add(Widgets.createArrow(new Point(centerX + 70, centerY - 15)));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 120;
    }
}
