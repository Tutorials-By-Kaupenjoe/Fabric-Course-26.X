package net.kaupenjoe.mccourse.compat;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.kaupenjoe.mccourse.recipe.custom.GrowthChamberRecipe;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public record GrowthChamberDisplay(List<EntryIngredient> inputs, EntryIngredient output,
                                   Optional<Identifier> location) implements Display {
    public static final DisplaySerializer<GrowthChamberDisplay> SERIALIZER = DisplaySerializer.of(
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    EntryIngredient.codec().listOf().fieldOf("inputs").forGetter(GrowthChamberDisplay::inputs),
                    EntryIngredient.codec().fieldOf("output").forGetter(GrowthChamberDisplay::output),
                    Identifier.CODEC.optionalFieldOf("location").forGetter(GrowthChamberDisplay::location)
            ).apply(instance, GrowthChamberDisplay::new)),
            StreamCodec.composite(
                    EntryIngredient.streamCodec().apply(ByteBufCodecs.list()),
                    GrowthChamberDisplay::inputs,
                    EntryIngredient.streamCodec(),
                    GrowthChamberDisplay::output,
                    ByteBufCodecs.optional(Identifier.STREAM_CODEC),
                    GrowthChamberDisplay::location,
                    GrowthChamberDisplay::new));

    public GrowthChamberDisplay(RecipeHolder<GrowthChamberRecipe> entry) {
        this(entry.id().identifier(), entry.value());
    }

    public GrowthChamberDisplay(Identifier id, GrowthChamberRecipe recipe) {
        this(EntryIngredients.ofIngredients(recipe.getIngredients()), EntryIngredients.of(recipe.output().create()), Optional.of(id));
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
        return MCCourseREICommon.GROWTH_CHAMBER;
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
