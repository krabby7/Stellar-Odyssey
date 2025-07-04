package net.krabby7.stellarodyssey.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.entity.custom.OverseerEntity;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class OverseerRenderer extends MobRenderer<OverseerEntity, OverseerModel> {
    /*
    private static final Map<PenguinVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(PenguinVariant.class), map -> {
                map.put(PenguinVariant.DEFAULT,
                        ResourceLocation.fromNamespaceAndPath(MCCourseMod.MOD_ID, "textures/entity/penguin/penguin.png"));
                map.put(PenguinVariant.ALBINO,
                        ResourceLocation.fromNamespaceAndPath(MCCourseMod.MOD_ID, "textures/entity/penguin/penguin_albino.png"));
                map.put(PenguinVariant.CLUB,
                        ResourceLocation.fromNamespaceAndPath(MCCourseMod.MOD_ID, "textures/entity/penguin/penguin_club.png"));
            });
     */

    public OverseerRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new OverseerModel(pContext.bakeLayer(ModModelLayers.OVERSEER)), 0.75f);
    }
    
    @Override
    public ResourceLocation getTextureLocation(OverseerEntity pEntity) {
        return  ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID, "textures/entity/overseer/overseer.png");
    }

    @Override
    public void render(OverseerEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.45f, 0.45f, 0.45f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}