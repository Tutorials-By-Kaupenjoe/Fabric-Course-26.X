package net.kaupenjoe.mccourse.data;

import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DataComponentType<BlockPos> COORDINATES =
            register("coordinates", builder -> builder.persistent(BlockPos.CODEC)
                    .networkSynchronized(BlockPos.STREAM_CODEC).cacheEncoding());


    private static <T>DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name),
                builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void registerDataComponents() {
        MCCourse.LOGGER.info("Registering Data Components for " + MCCourse.MOD_ID);
    }
}
