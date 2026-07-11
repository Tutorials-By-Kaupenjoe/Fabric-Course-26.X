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

import java.util.LinkedList;
import java.util.List;

// Done with the help:
// https://github.com/TeamGalacticraft/Galacticraft/tree/main (MIT License)
public class AtomicSeparatorCategory implements DisplayCategory<Display> {
    public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "textures/gui/atomic_separator/atomic_separator_gui.png");

    @Override
    public CategoryIdentifier<? extends Display> getCategoryIdentifier() {
        return MCCourseREICommon.ATOMIC_SEPARATOR;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Atomic Separator");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.ATOMIC_SEPARATOR.asItem().getDefaultInstance());
    }

    @Override
    public List<Widget> setupDisplay(Display display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 11))
                .entries(display.getInputEntries().get(0)).markInput());

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 46, startPoint.y + 55))
                .entries(display.getOutputEntries().get(0)).markOutput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 55))
                .entries(display.getOutputEntries().get(1)).markOutput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 114, startPoint.y + 55))
                .entries(display.getOutputEntries().get(2)).markOutput());

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}
