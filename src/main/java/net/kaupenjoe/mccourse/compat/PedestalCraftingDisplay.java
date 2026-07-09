package net.kaupenjoe.mccourse.compat;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.kaupenjoe.mccourse.recipe.custom.PedestalRecipe;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public record PedestalCraftingDisplay(List<EntryIngredient> inputs, EntryIngredient output,
                                      Optional<Identifier> location) implements Display {
    public static final DisplaySerializer<PedestalCraftingDisplay> SERIALIZER = DisplaySerializer.of(
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    EntryIngredient.codec().listOf().fieldOf("input").forGetter(PedestalCraftingDisplay::inputs),
                    EntryIngredient.codec().fieldOf("output").forGetter(PedestalCraftingDisplay::output),
                    Identifier.CODEC.optionalFieldOf("location").forGetter(PedestalCraftingDisplay::location)
            ).apply(instance, PedestalCraftingDisplay::new)), StreamCodec.composite(
                    EntryIngredient.streamCodec().apply(ByteBufCodecs.list()),
                    PedestalCraftingDisplay::inputs,
                    EntryIngredient.streamCodec(),
                    PedestalCraftingDisplay::output,
                    ByteBufCodecs.optional(Identifier.STREAM_CODEC),
                    PedestalCraftingDisplay::location,
                    PedestalCraftingDisplay::new));

    public PedestalCraftingDisplay(RecipeHolder<PedestalRecipe> entry) {
        this(entry.id().identifier(), entry.value());
    }

    public PedestalCraftingDisplay(Identifier id, PedestalRecipe recipe) {
        this(EntryIngredients.ofIngredients(recipe.ingredients()), EntryIngredients.of(recipe.output().create()), Optional.of(id));
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return inputs;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return List.of(output);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return MCCourseREICommon.PEDESTAL_CRAFTING;
    }

    @Override
    public Optional<Identifier> getDisplayLocation() {
        return location;
    }

    @Override
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return SERIALIZER;
    }
}
