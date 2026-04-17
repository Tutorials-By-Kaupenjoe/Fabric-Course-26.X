package net.kaupenjoe.mccourse.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.resources.RegistryDataLoader;
import org.jetbrains.annotations.Unmodifiable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

// Shoutout to KaboomRoads for this Solution!
@Mixin(value = FabricDynamicRegistryProvider.Entries.class, remap = false)
public abstract class FabricDynamicRegistryProviderMixin {
    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/api/event/registry/DynamicRegistries;getDynamicRegistries()Ljava/util/List;", remap = false), remap = false)
    private @Unmodifiable List<RegistryDataLoader.RegistryData<?>> wrapDynamicRegistries(Operation<List<RegistryDataLoader.RegistryData<?>>> original) {
        List<RegistryDataLoader.RegistryData<?>> dynamicRegistries = new ArrayList<>(original.call());
        dynamicRegistries.addAll(RegistryDataLoader.DIMENSION_REGISTRIES);
        return dynamicRegistries;
    }
}