package net.krabby7.stellarodyssey.event;
import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.entity.*;
import net.krabby7.stellarodyssey.entity.client.ModModelLayers;
import net.krabby7.stellarodyssey.entity.client.OverseerModel;
import net.krabby7.stellarodyssey.entity.custom.MummyEndermanEntity;
import net.krabby7.stellarodyssey.entity.custom.OverseerEntity;
import net.krabby7.stellarodyssey.worldgen.biome.ModBiomes;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import static net.minecraft.world.entity.monster.Monster.checkAnyLightMonsterSpawnRules;
import static net.minecraft.world.entity.monster.Monster.checkMonsterSpawnRules;

@EventBusSubscriber(modid = StellarOdyssey.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.OVERSEER, OverseerModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MUMMY_ENDERMAN.get(), MummyEndermanEntity.createAttributes().build());
        event.put(ModEntities.OVERSEER.get(), OverseerEntity.createAttributes().build());
    }


    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {

        event.register(ModEntities.MUMMY_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            ModEventBusEvents::checkSolarDesertMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(EntityType.ENDERMAN, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ModEventBusEvents::checkSolarDesertMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);


        event.register(ModEntities.OVERSEER.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ModEventBusEvents::checkSolarDesertMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

    }


    public static boolean checkSolarDesertMonsterSpawnRules(EntityType<? extends Monster> type, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        //level.getBiome(pos).equals(ModBiomes.SOLAR_DESERT) &&
        return  level.getBiome(pos).is(ModBiomes.SOLAR_DESERT) && checkAnyLightMonsterSpawnRules(type, level, spawnType, pos, random) || checkMonsterSpawnRules(type, (ServerLevelAccessor) level, spawnType, pos, random) ;
    }
}
