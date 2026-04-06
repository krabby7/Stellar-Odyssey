package net.krabby7.stellarodyssey.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;
import net.neoforged.neoforge.event.entity.player.PlayerRespawnPositionEvent;

public class RecallingEffect extends InstantenousMobEffect {
    public RecallingEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {

        double d0 = 0;
        double d1 = 0;
        double d2 = 0;

        if (!livingEntity.level().isClientSide) {
        }
            for(int i = 0; i < 16; ++i) {
                d0 = (double) livingEntity.level().getSharedSpawnPos().getX();
                d1 = (double) livingEntity.level().getSharedSpawnPos().getY();
                d2 = (double) livingEntity.level().getSharedSpawnPos().getZ();

                if (livingEntity instanceof Player player) {
                    if (player instanceof ServerPlayer sPlayer) {
                        if(sPlayer.getRespawnPosition() != null) {
                            DimensionTransition dimensiontransition = ((ServerPlayer) player).findRespawnPositionAndUseSpawnBlock(true, DimensionTransition.DO_NOTHING);
                            PlayerRespawnPositionEvent event = EventHooks.firePlayerRespawnPositionEvent((ServerPlayer) player, dimensiontransition, true);
                            dimensiontransition = event.getDimensionTransition();
                            if(!dimensiontransition.missingRespawnBlock()) {
                                d0 = (double) sPlayer.getRespawnPosition().getX();
                                d1 = (double) sPlayer.getRespawnPosition().getY() + 1;
                                d2 = (double) sPlayer.getRespawnPosition().getZ();
                            }
                        }
                    }
                }
                        if (livingEntity.isPassenger()) {
                            livingEntity.stopRiding();
                        }

                        livingEntity.teleportTo((int)d0, (int)d1, (int)d2);
                        Vec3 vec3 = livingEntity.position();
                        EntityTeleportEvent.ChorusFruit event = EventHooks.onChorusFruitTeleport(livingEntity, d0, d1, d2);

                            livingEntity.level().gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(livingEntity));
                            SoundSource soundsource;
                            SoundEvent soundevent;
                            soundevent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                            soundsource = SoundSource.PLAYERS;


                            livingEntity.level().playSound((Player)null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), soundevent, soundsource);

                            livingEntity.resetFallDistance();
                            break;

                    }
        return true;
    }

    public void applyInstantenousEffect(LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.level().isClientSide) {
            for(int i = 0; i < 16; ++i) {
                if (livingEntity.getType().equals(EntityType.PLAYER)) {
                    Player player = (Player) livingEntity;
                    player.respawn();
                } else {
                    double d0 = livingEntity.getX() + (livingEntity.getRandom().nextDouble() - (double) 0.5F) * ((double) 100.0F + ((double) 100.0F * amplifier));
                    double d1 = Mth.clamp(livingEntity.getY() + (double) (livingEntity.getRandom().nextInt(16) - 8), (double) livingEntity.level().getMinBuildHeight(), (double) (livingEntity.level().getMinBuildHeight() + ((ServerLevel) livingEntity.level()).getLogicalHeight() - 1));
                    double d2 = livingEntity.getZ() + (livingEntity.getRandom().nextDouble() - (double) 0.5F) * ((double) 100.0F + ((double) 100.0F * amplifier));
                    if (livingEntity.isPassenger()) {
                        livingEntity.stopRiding();
                    }

                    Vec3 vec3 = livingEntity.position();
                    EntityTeleportEvent.ChorusFruit event = EventHooks.onChorusFruitTeleport(livingEntity, d0, d1, d2);

                    if (livingEntity.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true)) {
                        livingEntity.level().gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(livingEntity));
                        SoundSource soundsource;
                        SoundEvent soundevent;
                        if (livingEntity instanceof Fox) {
                            soundevent = SoundEvents.FOX_TELEPORT;
                            soundsource = SoundSource.NEUTRAL;
                        } else {
                            soundevent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                            soundsource = SoundSource.PLAYERS;
                        }

                        livingEntity.level().playSound((Player) null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), soundevent, soundsource);
                        livingEntity.resetFallDistance();
                        break;
                    }
                }
            }
        }
    }
}
