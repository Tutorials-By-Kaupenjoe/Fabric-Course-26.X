package net.kaupenjoe.mccourse.entity.client;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class GiraffeModel extends EntityModel<GiraffeRenderState> {
    private final ModelPart body;
    private final ModelPart torso;
    private final ModelPart neck;
    private final ModelPart head;

    private final KeyframeAnimation walkingAnimation;
    private final KeyframeAnimation idlingAnimation;

    public GiraffeModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.torso = this.body.getChild("torso");
        this.neck = this.torso.getChild("neck");
        this.head = this.neck.getChild("head");

        this.walkingAnimation = GiraffeAnimations.WALK.bake(root);
        this.idlingAnimation = GiraffeAnimations.IDLE.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -8.0F, -17.0F, 18.0F, 18.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -43.0F, 0.0F));

        PartDefinition tail = torso.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5.5F, 0.0F, 0.0F, 11.0F, 33.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.0F, 17.0F, 0.0436F, 0.0F, 0.0F));

        PartDefinition tip_r1 = tail.addOrReplaceChild("tip_r1", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-5.5F, -4.5F, 0.0F, 11.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 28.5F, 0.0F, 0.0F, 2.2689F, 0.0F));

        PartDefinition tip_r2 = tail.addOrReplaceChild("tip_r2", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-5.5F, -4.5F, 0.0F, 11.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 28.5F, 0.0F, 0.0F, -2.2689F, 0.0F));

        PartDefinition neck = torso.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(99, 93).addBox(-3.5F, -23.0F, -7.0F, 7.0F, 28.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(100, 72).addBox(-3.5F, -38.0F, -7.0F, 7.0F, 15.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(100, 53).addBox(-3.5F, -51.0F, -7.0F, 7.0F, 13.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 52).addBox(-6.5F, -7.0F, -6.0F, 13.0F, 16.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -13.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(71, 18).addBox(-2.5F, -4.0F, -11.5F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(70, 0).addBox(-4.5F, -8.0F, -5.5F, 9.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -51.0F, -3.5F));

        PartDefinition horns = head.addOrReplaceChild("horns", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

        PartDefinition hornL = horns.addOrReplaceChild("hornL", CubeListBuilder.create().texOffs(22, 9).addBox(-0.5F, -4.0F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
                .texOffs(22, 5).addBox(-0.5F, -3.0F, 4.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, -5.0F));

        PartDefinition hornR = horns.addOrReplaceChild("hornR", CubeListBuilder.create().texOffs(22, 5).addBox(-0.5F, -3.0F, 4.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 9).addBox(-0.5F, -4.0F, 4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offset(-3.0F, 0.0F, -5.0F));

        PartDefinition earL = head.addOrReplaceChild("earL", CubeListBuilder.create(), PartPose.offset(4.456F, -5.7557F, -2.0F));

        PartDefinition cube_r1 = earL.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(22, 0).mirror().addBox(-0.75F, -3.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -0.75F, 0.5F, -0.2618F, 0.0F, 1.0036F));

        PartDefinition earR = head.addOrReplaceChild("earR", CubeListBuilder.create(), PartPose.offset(-4.456F, -5.7557F, -2.0F));

        PartDefinition cube_r2 = earR.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(22, 0).addBox(-2.25F, -3.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -0.75F, 0.5F, -0.2618F, 0.0F, -1.0036F));

        PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition legFR = legs.addOrReplaceChild("legFR", CubeListBuilder.create().texOffs(76, 52).addBox(-2.5F, -2.0F, -2.5F, 5.0F, 40.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(8.5F, -38.0F, -11.5F));

        PartDefinition legFL = legs.addOrReplaceChild("legFL", CubeListBuilder.create().texOffs(76, 52).addBox(-11.0F, -2.0F, -2.5F, 5.0F, 40.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -38.0F, -11.5F));

        PartDefinition legBR = legs.addOrReplaceChild("legBR", CubeListBuilder.create().texOffs(56, 52).addBox(6.0F, 10.0F, 1.0F, 5.0F, 27.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 79).addBox(6.0F, -3.0F, -5.0F, 5.0F, 13.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -37.0F, 10.0F));

        PartDefinition legBL = legs.addOrReplaceChild("legBL", CubeListBuilder.create().texOffs(0, 79).addBox(-11.0F, -3.0F, -5.0F, 5.0F, 13.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(56, 52).addBox(-11.0F, 10.0F, 1.0F, 5.0F, 27.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -37.0F, 10.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(GiraffeRenderState state) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(state.yRot, state.xRot);

        this.walkingAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f);
        this.idlingAnimation.apply(state.idleAnimationState, state.ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.head.yRot = headYaw * ((float)Math.PI / 180f);
        this.head.xRot = headPitch *  ((float)Math.PI / 180f);
    }
}
