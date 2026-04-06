package net.krabby7.stellarodyssey.entity.client;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import  net.minecraft.world.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.krabby7.stellarodyssey.entity.custom.NovaTickEntity;

public class NovaTickModel extends HierarchicalModel<NovaTickEntity> {
    private final ModelPart body;
    private final ModelPart butt;
    private final ModelPart legs;
    private final ModelPart leg1;
    private final ModelPart leg4;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart head;
    private final ModelPart mandibles;
    private final ModelPart mandible1;
    private final ModelPart mandible2;
    private final ModelPart antennas;
    private final ModelPart antenna1;
    private final ModelPart antenna2;

    public NovaTickModel(ModelPart root) {
        this.body = root.getChild("body");
        this.butt = this.body.getChild("butt");
        this.legs = this.body.getChild("legs");
        this.leg1 = this.legs.getChild("leg1");
        this.leg4 = this.legs.getChild("leg4");
        this.leg2 = this.legs.getChild("leg2");
        this.leg3 = this.legs.getChild("leg3");
        this.head = root.getChild("head");
        this.mandibles = this.head.getChild("mandibles");
        this.mandible1 = this.mandibles.getChild("mandible1");
        this.mandible2 = this.mandibles.getChild("mandible2");
        this.antennas = this.head.getChild("antennas");
        this.antenna1 = this.antennas.getChild("antenna1");
        this.antenna2 = this.antennas.getChild("antenna2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -7.0F, -15.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 25.0F, 5.0F));

        PartDefinition butt = body.addOrReplaceChild("butt", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -1.0F));

        PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leg1 = legs.addOrReplaceChild("leg1", CubeListBuilder.create(), PartPose.offset(0.6844F, -5.5704F, -10.5F));

        PartDefinition cube_r1 = leg1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 16).addBox(-1.0F, -1.0F, 0.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3156F, 0.5704F, -0.5F, 0.0F, 0.0F, 0.6545F));

        PartDefinition leg4 = legs.addOrReplaceChild("leg4", CubeListBuilder.create(), PartPose.offset(-0.6844F, -5.5704F, -10.5F));

        PartDefinition cube_r2 = leg4.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 22).addBox(-7.0F, -1.0F, 0.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3156F, 0.5704F, -0.5F, 0.0F, 0.0F, -0.6545F));

        PartDefinition leg2 = legs.addOrReplaceChild("leg2", CubeListBuilder.create(), PartPose.offset(0.6844F, -5.5704F, -13.5F));

        PartDefinition cube_r3 = leg2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(16, 18).addBox(-1.0F, -1.0F, 0.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3156F, 0.5704F, -0.5F, 0.0F, 0.0F, 0.6545F));

        PartDefinition leg3 = legs.addOrReplaceChild("leg3", CubeListBuilder.create(), PartPose.offset(-0.6844F, -5.5704F, -13.5F));

        PartDefinition cube_r4 = leg3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(16, 20).addBox(-7.0F, -1.0F, 0.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3156F, 0.5704F, -0.5F, 0.0F, 0.0F, -0.6545F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 24).addBox(-1.5F, -6.5F, -7.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -6.0F));

        PartDefinition mandibles = head.addOrReplaceChild("mandibles", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition mandible1 = mandibles.addOrReplaceChild("mandible1", CubeListBuilder.create(), PartPose.offset(-1.0F, -4.0F, -7.0F));

        PartDefinition cube_r5 = mandible1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(12, 24).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.6109F, 0.0F));

        PartDefinition mandible2 = mandibles.addOrReplaceChild("mandible2", CubeListBuilder.create(), PartPose.offset(1.0F, -4.0F, -7.0F));

        PartDefinition cube_r6 = mandible2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(18, 24).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6109F, 0.0F));

        PartDefinition antennas = head.addOrReplaceChild("antennas", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition antenna1 = antennas.addOrReplaceChild("antenna1", CubeListBuilder.create().texOffs(16, 27).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -6.5F, -6.5F, 0.5672F, 0.6109F, 0.0F));

        PartDefinition cube_r7 = antenna1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(24, 24).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.6981F, 0.0F, 0.0F));

        PartDefinition antenna2 = antennas.addOrReplaceChild("antenna2", CubeListBuilder.create().texOffs(20, 27).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -6.5F, -6.5F, 0.5672F, -0.6109F, 0.0F));

        PartDefinition cube_r8 = antenna2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(12, 27).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.6981F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(NovaTickEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(NovaTickAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(NovaTickEntity.idleAnimationState, NovaTickAnimations.IDLE, ageInTicks, 1f);
        this.animate(NovaTickEntity.attackAnimState, NovaTickAnimations.BITE, ageInTicks, 1f);
    }




    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -32, 32);
        headPitch = Mth.clamp(headPitch, -32, 32);


        this.head.yRot = headYaw * ((float)Math.PI / 180f);
        this.head.xRot = headPitch *  ((float)Math.PI / 180f);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return body;
    }
}
