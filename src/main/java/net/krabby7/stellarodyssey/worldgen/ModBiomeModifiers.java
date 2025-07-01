package net.krabby7.stellarodyssey.worldgen;

import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.entity.ModEntities;
import net.krabby7.stellarodyssey.worldgen.biome.ModBiomes;
import net.krabby7.stellarodyssey.worldgen.biome.ModEndBiomes;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_TREE_EBONY = registerKey("add_tree_ebony");

    public static final ResourceKey<BiomeModifier> ADD_BLACK_OPAL_ORE = registerKey("add_black_opal_ore");
    public static final ResourceKey<BiomeModifier> ADD_NETHER_BLACK_OPAL_ORE = registerKey("add_nether_black_opal_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_BLACK_OPAL_ORE = registerKey("add_end_black_opal_ore");

    public static final ResourceKey<BiomeModifier> ADD_SUNBURNT_SHRUB = registerKey("add_sunburnt_shrub");

    public static final ResourceKey<BiomeModifier> ADD_DRAGON_CACTUS = registerKey("add_dragon_cactus");

    public static final ResourceKey<BiomeModifier> SPAWN_MUMMY_ENDERMAN = registerKey("spawn_mummy_enderman");

    public static final ResourceKey<BiomeModifier> SPAWN_MUMMY_ENDERMAN_2 = registerKey("spawn_mummy_enderman_2");

    public static final ResourceKey<BiomeModifier> ADD_BLACK_OPAL_GEODE = registerKey("add_black_opal_geode");


    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);


        context.register(ADD_SUNBURNT_SHRUB, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.SOLAR_DESERT)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SUNBURNT_SHRUB_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_DRAGON_CACTUS, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.SOLAR_DESERT)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DRAGON_CACTUS_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(SPAWN_MUMMY_ENDERMAN, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.DESERT), biomes.getOrThrow(Biomes.BADLANDS)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.MUMMY_ENDERMAN.get(), 3, 1, 2))));


        context.register(SPAWN_MUMMY_ENDERMAN_2, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.SOLAR_DESERT)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.MUMMY_ENDERMAN.get(), 7, 1, 3))));

    }


    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID, name));
    }
}
