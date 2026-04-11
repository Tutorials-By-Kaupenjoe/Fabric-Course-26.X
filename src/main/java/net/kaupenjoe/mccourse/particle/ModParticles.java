package net.kaupenjoe.mccourse.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.kaupenjoe.mccourse.MCCourse;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModParticles {
    public static final SimpleParticleType BISMUTH_PARTICLE =
            registerParticle("bismuth_particle", FabricParticleTypes.simple());


    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        MCCourse.LOGGER.info("Registering Particles for " + MCCourse.MOD_ID);
    }
}
