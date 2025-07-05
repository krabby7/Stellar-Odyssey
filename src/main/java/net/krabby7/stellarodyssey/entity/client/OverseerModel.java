package net.krabby7.stellarodyssey.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.krabby7.stellarodyssey.entity.custom.OverseerEntity;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import  net.minecraft.world.*;


public class OverseerModel extends HierarchicalModel<OverseerEntity> {
    private final ModelPart body;
    private final ModelPart tendrils;
    private final ModelPart tendrill2;
    private final ModelPart tendrill1;
    private final ModelPart tendrill3;

    public OverseerModel(ModelPart root) {
        this.body = root.getChild("body");
        this.tendrils = this.body.getChild("tendrils");
        this.tendrill2 = this.tendrils.getChild("tendrill2");
        this.tendrill1 = this.tendrils.getChild("tendrill1");
        this.tendrill3 = this.tendrils.getChild("tendrill3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));

        PartDefinition tendrils = body.addOrReplaceChild("tendrils", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 0.0F));

        PartDefinition tendrill2 = tendrils.addOrReplaceChild("tendrill2", CubeListBuilder.create(), PartPose.offset(3.0F, -4.0F, 4.0F));

        PartDefinition cube_r1 = tendrill2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 20).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 1.0F, -0.3054F, 0.0F, 0.0F));

        PartDefinition tendrill1 = tendrils.addOrReplaceChild("tendrill1", CubeListBuilder.create(), PartPose.offset(-3.0F, -4.0F, 4.0F));

        PartDefinition cube_r2 = tendrill1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 20).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 1.0F, -0.3054F, 0.0F, 0.0F));

        PartDefinition tendrill3 = tendrils.addOrReplaceChild("tendrill3", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 4.0F));

        PartDefinition cube_r3 = tendrill3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 1.0F, -0.3054F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(OverseerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(OverseerAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(OverseerEntity.idleAnimationState, OverseerAnimations.IDLE, ageInTicks, 1f);
        this.animate(OverseerEntity.attackAnimState, OverseerAnimations.SPIN, ageInTicks, 1f);
    }




    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -60, 60);
        headPitch = Mth.clamp(headPitch, -25f, 45);


        this.body.yRot = headYaw * ((float)Math.PI / 180f);
        this.body.xRot = headPitch *  ((float)Math.PI / 180f);
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
