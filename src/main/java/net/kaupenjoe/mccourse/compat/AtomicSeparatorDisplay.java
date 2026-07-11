package net.kaupenjoe.mccourse.compat;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.kaupenjoe.mccourse.recipe.custom.AtomicSeparatorRecipe;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public record AtomicSeparatorDisplay(EntryIngredient input, List<EntryIngredient> outputs,
                                     Optional<Identifier> location) implements Display {
    public static final DisplaySerializer<AtomicSeparatorDisplay> SERIALIZER = DisplaySerializer.of(
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    EntryIngredient.codec().fieldOf("input").forGetter(AtomicSeparatorDisplay::input),
                    EntryIngredient.codec().listOf().fieldOf("outputs").forGetter(AtomicSeparatorDisplay::outputs),
                    Identifier.CODEC.optionalFieldOf("location").forGetter(AtomicSeparatorDisplay::location)
            ).apply(instance, AtomicSeparatorDisplay::new)),
            StreamCodec.composite(
                    EntryIngredient.streamCodec(),
                    AtomicSeparatorDisplay::input,
                    EntryIngredient.streamCodec().apply(ByteBufCodecs.list()),
                    AtomicSeparatorDisplay::outputs,
                    ByteBufCodecs.optional(Identifier.STREAM_CODEC),
                    AtomicSeparatorDisplay::location,
                    AtomicSeparatorDisplay::new));

    public AtomicSeparatorDisplay(RecipeHolder<AtomicSeparatorRecipe> entry) {
        this(entry.id().identifier(), entry.value());
    }

    public AtomicSeparatorDisplay(Identifier id, AtomicSeparatorRecipe recipe) {
        this(EntryIngredients.ofIngredient(recipe.inputItem()), EntryIngredients.ofIngredients(recipe.getOutputs()
                .stream().map(stack -> Ingredient.of(stack.getItem())).toList()), Optional.of(id));
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return List.of(input);
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return outputs;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return MCCourseREICommon.ATOMIC_SEPARATOR;
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
