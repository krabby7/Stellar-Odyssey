package net.krabby7.stellarodyssey.worldgen;

import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.block.ModBlocks;
import net.krabby7.stellarodyssey.block.custom.DragonCactusBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.fml.common.Mod;
import org.apache.commons.codec.language.bm.Rule;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> EBONY_KEY = registerKey("ebony");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_BLACK_OPAL_ORE_KEY = registerKey("black_opal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_BLACK_OPAL_ORE_KEY = registerKey("nether_black_opal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_BLACK_OPAL_ORE_KEY = registerKey("end_black_opal_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SUNBURNT_SHRUB_KEY = registerKey("sunburnt_shrub");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SUNCCULENT_KEY = registerKey("suncculent");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DRAGON_CACTUS_KEY = registerKey("dragon_cactus");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_OPAL_GEODE_KEY = registerKey("black_opal_geode");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        /*
        register(context, EBONY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.EBONY_LOG.get()),
                new StraightTrunkPlacer(4, 5, 3),
                BlockStateProvider.simple(ModBlocks.EBONY_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(4), ConstantInt.of(2), 4),
                new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(Blocks.NETHERRACK)).build());

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldBlackOpalOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.BLACK_OPAL_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.BLACK_OPAL_DEEPSLATE_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_BLACK_OPAL_ORE_KEY, Feature.ORE, new OreConfiguration(overworldBlackOpalOres, 9));
        register(context, NETHER_BLACK_OPAL_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables,
                ModBlocks.BLACK_OPAL_NETHER_ORE.get().defaultBlockState(), 9));
        register(context, END_BLACK_OPAL_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
                ModBlocks.BLACK_OPAL_END_ORE.get().defaultBlockState(), 9));

        register(context, PETUNIA_KEY, Feature.FLOWER, new RandomPatchConfiguration(32, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PETUNIA.get())))));


        register(context, BLACK_OPAL_GEODE_KEY, Feature.GEODE,
                new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(Blocks.DEEPSLATE),
                        BlockStateProvider.simple(ModBlocks.BLACK_OPAL_ORE.get()),
                        BlockStateProvider.simple(Blocks.DIRT),
                        BlockStateProvider.simple(Blocks.EMERALD_BLOCK),
                        List.of(ModBlocks.BLACK_OPAL_BLOCK.get().defaultBlockState()),
                        BlockTags.FEATURES_CANNOT_REPLACE , BlockTags.GEODE_INVALID_BLOCKS),

                        new GeodeLayerSettings(1.7D, 1.2D, 2.5D, 3.5D),
                        new GeodeCrackSettings(0.25D, 1.5D, 1), 0.5D, 0.1D,
                        true, UniformInt.of(3, 8),
                        UniformInt.of(2, 6), UniformInt.of(1, 2),
                        -18, 18, 0.075D, 1));

         */

        register(context, SUNBURNT_SHRUB_KEY, Feature.FLOWER, new RandomPatchConfiguration(32, 32, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SUNBURNT_SHRUB.get())))));

        register(context, DRAGON_CACTUS_KEY, Feature.FLOWER, new RandomPatchConfiguration(32, 64, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.DRAGON_CACTUS.get().defaultBlockState().setValue(DragonCactusBlock.AGE, Integer.valueOf(2)))))));

        register(context, SUNCCULENT_KEY, Feature.FLOWER, new RandomPatchConfiguration(12, 32, 2,
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
