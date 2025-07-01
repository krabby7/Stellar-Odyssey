package net.krabby7.stellarodyssey.entity.client;

import net.krabby7.stellarodyssey.StellarOdyssey;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.EnderEyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.EnderMan;

public class MummyEndermanRenderer extends EndermanRenderer {
    public MummyEndermanRenderer(EntityRendererProvider.Context p_173992_) {
        super(p_173992_);


        for (int i = 0; i < this.layers.size(); i++){
            if(this.layers.get(i) instanceof EnderEyesLayer<EnderMan>){
                this.layers.remove(this.layers.get(i));
            }
        }
    }

    private static final ResourceLocation ENDERMAN_LOCATION = ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID, "textures/entity/mummy_enderman.png");

    @Override
    public ResourceLocation getTextureLocation(EnderMan entity) {
        return ENDERMAN_LOCATION;
    }

}
