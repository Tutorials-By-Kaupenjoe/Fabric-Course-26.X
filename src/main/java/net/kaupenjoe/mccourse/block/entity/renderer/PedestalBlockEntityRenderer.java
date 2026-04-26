package net.kaupenjoe.mccourse.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.kaupenjoe.mccourse.block.entity.custom.PedestalBlockEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector2i;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity, PedestalBlockEntityRenderState> {
    private final ItemModelResolver itemModelResolver;
    private final BlockModelResolver blockResolver;
    private final BlockModelRenderState blockRenderState = new BlockModelRenderState();
    List<Vector2i> offsets = List.of(
            new Vector2i(3, 0),
            new Vector2i(2, 2),
            new Vector2i(0, 3),
            new Vector2i(2, -2),

            new Vector2i(-2, 2),
            new Vector2i(-2, -2),
            new Vector2i(0, -3),
            new Vector2i(-3, 0));

    public PedestalBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        itemModelResolver = context.itemModelResolver();
        blockResolver = context.blockModelResolver();
    }

    @Override
    public PedestalBlockEntityRenderState createRenderState() {
        return new PedestalBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(PedestalBlockEntity blockEntity, PedestalBlockEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        state.level = blockEntity.getLevel();
        state.rotation = (blockEntity.getLevel().getGameTime() + partialTicks * 0.5f) % 360f;

        itemModelResolver.updateForTopItem(state.itemStackRenderState,
                blockEntity.getTheItem(), ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);
        blockResolver.update(blockRenderState, blockEntity.getBlockState(), BlockDisplayContext.create());
    }

    @Override
    public void submit(PedestalBlockEntityRenderState state, PoseStack poseStack,
                       SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();

        poseStack.translate(0.5f, 1.15f, 0.5f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(state.rotation));

        state.itemStackRenderState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);

        poseStack.popPose();

        offsets.forEach(offset -> {
            if(state.level.getBlockState(state.blockPos.offset(offset.x, 0, offset.y)).isAir()) {
                renderSidePedestal(poseStack, submitNodeCollector, offset.x, offset.y);
            }
        });
    }

    private void renderSidePedestal(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, float xOffset, float zOffset) {
        poseStack.pushPose();
        poseStack.translate(xOffset, 0f, zOffset);
        blockRenderState.submit(poseStack, submitNodeCollector, 400, OverlayTexture.RED_OVERLAY_V, 0);
        poseStack.popPose();
    }
}
