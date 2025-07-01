package net.krabby7.stellarodyssey.worldgen.biome;

import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.worldgen.biome.region.NetherRegion;
import net.krabby7.stellarodyssey.worldgen.biome.region.OverworldRegion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import terrablender.api.EndBiomeRegistry;
import terrablender.api.Regions;

public class ModBiomes {
    public static final ResourceKey<Biome> SOLAR_DESERT = registerBiomeKey("solar_desert");

    public static void registerBiomes() {
        EndBiomeRegistry.registerMidlandsBiome(SOLAR_DESERT, 2);
        EndBiomeRegistry.registerHighlandsBiome(SOLAR_DESERT, 2);
        //EndBiomeRegistry.registerEdgeBiome(SOLAR_DESERT, 1000);
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        var carver = context.lookup(Registries.CONFIGURED_CARVER);
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        //register(context, KAUPEN_VALLEY, ModOverworldBiomes.kaupenValley(placedFeatures, carver));
        //register(context, GLOWSTONE_PLAIN, ModNetherBiomes.glowstonePlains(placedFeatures, carver));
        //register(context, END_ROT, ModEndBiomes.endRot(placedFeatures, carver));
        register(context, SOLAR_DESERT, ModEndBiomes.solarDesert(placedFeatures, carver));
    }


    private static void register(BootstrapContext<Biome> context, ResourceKey<Biome> key, Biome biome) {
        context.register(key, biome);
    }

    private static ResourceKey<Biome> registerBiomeKey(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID, name));
    }
}
