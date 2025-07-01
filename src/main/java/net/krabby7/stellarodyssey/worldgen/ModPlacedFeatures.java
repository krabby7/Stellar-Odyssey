package net.krabby7.stellarodyssey.worldgen;

import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.block.ModBlocks;
import net.krabby7.stellarodyssey.worldgen.biome.ModEndBiomes;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> EBONY_PLACED_KEY = registerKey("ebony_placed");

    public static final ResourceKey<PlacedFeature> BLACK_OPAL_ORE_PLACED_KEY = registerKey("black_opal_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_BLACK_OPAL_ORE_PLACED_KEY = registerKey("nether_black_opal_ore_placed");
    public static final ResourceKey<PlacedFeature> END_BLACK_OPAL_ORE_PLACED_KEY = registerKey("end_black_opal_ore_placed");

    public static final ResourceKey<PlacedFeature> SUNBURNT_SHRUB_PLACED_KEY = registerKey("sunburnt_shrub_placed");

    public static final ResourceKey<PlacedFeature> DRAGON_CACTUS_PLACED_KEY = registerKey("dragon_cactus_placed");
    public static final ResourceKey<PlacedFeature> BLACK_OPAL_GEODE_PLACED_KEY = registerKey("black_opal_geode_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        /* register(context, EBONY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.EBONY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBlocks.EBONY_SAPLING.get())); */


        register(context,SUNBURNT_SHRUB_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SUNBURNT_SHRUB_KEY),
                List.of(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context,DRAGON_CACTUS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DRAGON_CACTUS_KEY),
                List.of(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
