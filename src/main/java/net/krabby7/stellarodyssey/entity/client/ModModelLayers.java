package net.krabby7.stellarodyssey.entity.client;
import net.krabby7.stellarodyssey.StellarOdyssey;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation OVERSEER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID, "overseer"), "main");

    public static final ModelLayerLocation NOVA_TICK = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID, "nova_tick"), "main");

}