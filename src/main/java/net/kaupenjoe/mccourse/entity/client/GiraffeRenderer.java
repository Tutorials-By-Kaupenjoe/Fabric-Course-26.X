package net.kaupenjoe.mccourse.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.entity.custom.GiraffeEntity;
import net.kaupenjoe.mccourse.entity.variant.GiraffeVariant;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class GiraffeRenderer extends MobRenderer<GiraffeEntity, GiraffeRenderState, GiraffeModel> {
    private static final Map<GiraffeVariant, Identifier> TEXTURE_BY_VARIANT =
            Util.make(Maps.newEnumMap(GiraffeVariant.class), map -> {
                map.put(GiraffeVariant.SPOTTED,
                        Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "textures/entity/giraffe/giraffe_spotted.png"));
                map.put(GiraffeVariant.BLANK,
                        Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "textures/entity/giraffe/giraffe_blank.png"));
            });

    public GiraffeRenderer(EntityRendererProvider.Context context) {
        super(context, new GiraffeModel(context.bakeLayer(ModModelLayerLocations.GIRAFFE)), 0.8f);
    }

    @Override
    public Identifier getTextureLocation(GiraffeRenderState state) {
        return TEXTURE_BY_VARIANT.get(state.variant);
    }

    @Override
    public void submit(GiraffeRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector,
                       CameraRenderState camera) {
        if(state.isBaby) {
            poseStack.scale(0.25f,0.25f,0.25f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public GiraffeRenderState createRenderState() {
        return new GiraffeRenderState();
    }

    @Override
    public void extractRenderState(GiraffeEntity entity, GiraffeRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.variant = entity.getVariant();
    }
}
