package net.krabby7.stellarodyssey.potion;
import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, StellarOdyssey.MOD_ID);

    public static final Holder<Potion> TELEPORTATION_POTION = POTIONS.register("teleportation_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.TELEPORTATION_EFFECT, 1, 0)));

    public static final Holder<Potion> TELEPORTATION_POTION_2 = POTIONS.register("teleportation_potion_2",
            () -> new Potion(new MobEffectInstance(ModEffects.TELEPORTATION_EFFECT, 1, 1)));

    public static final Holder<Potion> RECALLING_POTION = POTIONS.register("recalling_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.RECALLING_EFFECT, 1, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}