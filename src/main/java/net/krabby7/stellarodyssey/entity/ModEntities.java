package net.krabby7.stellarodyssey.entity;
import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.entity.custom.MummyEndermanEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, StellarOdyssey.MOD_ID);

    public static final Supplier<EntityType<MummyEndermanEntity>> MUMMY_ENDERMAN =
            ENTITY_TYPES.register("mummy_enderman", () -> EntityType.Builder.of(MummyEndermanEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 3f).build("mummy_enderman"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}