package net.krabby7.stellarodyssey.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.entity.custom.NovaTickEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class NovaTickRenderer extends MobRenderer<NovaTickEntity, NovaTickModel> {

    public NovaTickRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new NovaTickModel(pContext.bakeLayer(ModModelLayers.NOVA_TICK)), 0.75f);
    }
    
    @Override
    public ResourceLocation getTextureLocation(NovaTickEntity pEntity) {
        return  ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID, "textures/entity/nova_tick.png");
    }

    @Override
    public void render(NovaTickEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.45f, 0.45f, 0.45f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}