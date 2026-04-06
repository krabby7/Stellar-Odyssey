package net.krabby7.stellarodyssey.worldgen;

import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.block.ModBlocks;
import net.krabby7.stellarodyssey.worldgen.biome.ModEndBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> EBONY_PLACED_KEY = registerKey("ebony_placed");

    public static final ResourceKey<PlacedFeature> CELESTEEL_ORE_PLACED_KEY = registerKey("celesteel_ore_placed");

    public static final ResourceKey<PlacedFeature> SUNBURNT_SHRUB_PLACED_KEY = registerKey("sunburnt_shrub_placed");

    public static final ResourceKey<PlacedFeature> SILVER_WEED_PLACED_KEY = registerKey("silver_weed_placed");

    public static final ResourceKey<PlacedFeature> SUNCCULENT_PLACED_KEY = registerKey("suncculent_placed");

    public static final ResourceKey<PlacedFeature> DRAGON_CACTUS_PLACED_KEY = registerKey("dragon_cactus_placed");
    public static final ResourceKey<PlacedFeature> BLACK_OPAL_GEODE_PLACED_KEY = registerKey("black_opal_geode_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        /* register(context, EBONY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.EBONY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBlocks.EBONY_SAPLING.get())); */


        register(context,SUNBURNT_SHRUB_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SUNBURNT_SHRUB_KEY),
                List.of(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context,SILVER_WEED_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVER_WEED_KEY),
                List.of(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));


        register(context,DRAGON_CACTUS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DRAGON_CACTUS_KEY),
                List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context,SUNCCULENT_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SUNCCULENT_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context, CELESTEEL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CELESTEEL_ORE_KEY),
                ModOrePlacements.commonOrePlacement(12,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> dragonCactusPlacedKey, Feature<RandomPatchConfiguration> randomPatch, RandomPatchConfiguration randomPatchConfiguration) {

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
