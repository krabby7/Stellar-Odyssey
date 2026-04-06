package net.krabby7.stellarodyssey.effect;
import net.krabby7.stellarodyssey.StellarOdyssey;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, StellarOdyssey.MOD_ID);

    public static final Holder<MobEffect> TELEPORTATION_EFFECT = MOB_EFFECTS.register("teleportation",
            () -> new TeleportationEffect(MobEffectCategory.NEUTRAL, 0x8907d9));

    public static final Holder<MobEffect> RECALLING_EFFECT = MOB_EFFECTS.register("recalling",
            () -> new RecallingEffect(MobEffectCategory.NEUTRAL, 0x22d3f6));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}