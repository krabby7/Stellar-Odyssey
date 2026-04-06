package net.krabby7.stellarodyssey.worldgen;

import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.block.ModBlocks;
import net.krabby7.stellarodyssey.block.custom.DragonCactusBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.fml.common.Mod;
import org.apache.commons.codec.language.bm.Rule;

import java.util.List;

import static net.krabby7.stellarodyssey.block.custom.DragonCactusBlock.AGE;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> EBONY_KEY = registerKey("ebony");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_BLACK_OPAL_ORE_KEY = registerKey("black_opal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_BLACK_OPAL_ORE_KEY = registerKey("nether_black_opal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CELESTEEL_ORE_KEY = registerKey("celesteel_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SUNBURNT_SHRUB_KEY = registerKey("sunburnt_shrub");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SUNCCULENT_KEY = registerKey("suncculent");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DRAGON_CACTUS_KEY = registerKey("dragon_cactus");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_WEED_KEY = registerKey("silver_weed");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_OPAL_GEODE_KEY = registerKey("black_opal_geode");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        register(context, CELESTEEL_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
                ModBlocks.CELESTEEL_ORE.get().defaultBlockState(), 5));


        register(context, SUNBURNT_SHRUB_KEY, Feature.FLOWER, new RandomPatchConfiguration(32, 32, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SUNBURNT_SHRUB.get())))));

        register(context, SILVER_WEED_KEY, Feature.FLOWER, new RandomPatchConfiguration(32, 32, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SILVER_WEED.get())))));


//        register(context, DRAGON_CACTUS_KEY, Feature.FLOWER, new RandomPatchConfiguration(32, 64, 2,
//                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.DRAGON_CACTUS.get().defaultBlockState().setValue(DragonCactusBlock.AGE, Integer.valueOf(2)))))));

        FeatureUtils.register(context, DRAGON_CACTUS_KEY, Feature.RANDOM_PATCH, FeatureUtils.simpleRandomPatchConfiguration(10, PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, BlockColumnConfiguration.simple(BiasedToBottomInt.of(4, 8), BlockStateProvider.simple(ModBlocks.DRAGON_CACTUS.get().defaultBlockState().setValue(AGE, 2))), new PlacementModifier[]{BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(Blocks.CACTUS.defaultBlockState(), BlockPos.ZERO)))})));


        register(context, SUNCCULENT_KEY, Feature.FLOWER, new RandomPatchConfiguration(6, 32, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SUNCCULENT.get())))));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
