package net.krabby7.stellarodyssey.enchantment.custom;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;

public record DislocatingEnchantmentEffect(int level) implements EnchantmentEntityEffect {
    public static final MapCodec<DislocatingEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(Codec.INT.fieldOf("level").forGetter(DislocatingEnchantmentEffect::level))
                    .apply(instance, DislocatingEnchantmentEffect::new));

    @Override
    public void apply(ServerLevel pLevel, int pEnchantmentLevel, EnchantedItemInUse pItem, Entity pEntity, Vec3 pOrigin) {

            int max = 8;
            int min = 1;
            int range = max - min + 1;
            int rand = (int) (Math.random() * range) + min;

            if(rand == 1) {
                double d0 = pEntity.getX() + (pEntity.getRandom().nextDouble() - (double)0.5F) * ((double)4.0F + ((double)4.0F * pEnchantmentLevel));
                double d1 = Mth.clamp(pEntity.getY() + (double)(pEntity.getRandom().nextInt(16) - 8), (double)pEntity.level().getMinBuildHeight(), (double)(pEntity.level().getMinBuildHeight() + ((ServerLevel)pEntity.level()).getLogicalHeight() - 1));
                double d2 = pEntity.getZ() + (pEntity.getRandom().nextDouble() - (double)0.5F) * ((double)4.0F + ((double)4.0F * pEnchantmentLevel));
                if (pEntity.isPassenger()) {
                    pEntity.stopRiding();
                }

                Vec3 vec3 = pEntity.position();
                EntityTeleportEvent.ChorusFruit event = EventHooks.onChorusFruitTeleport((LivingEntity) pEntity, d0, d1, d2);

                if (((LivingEntity) pEntity).randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true)) {
                    pEntity.level().gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(pEntity));
                    SoundSource soundsource;
                    SoundEvent soundevent;
                    if (pEntity instanceof Fox) {
                        soundevent = SoundEvents.FOX_TELEPORT;
                        soundsource = SoundSource.NEUTRAL;
                    } else {
                        soundevent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                        soundsource = SoundSource.PLAYERS;
                    }

                    pEntity.level().playSound((Player)null, pEntity.getX(), pEntity.getY(), pEntity.getZ(), soundevent, soundsource);
                    pEntity.resetFallDistance();
                }
            }
        }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}