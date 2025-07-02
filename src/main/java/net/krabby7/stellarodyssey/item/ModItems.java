package net.krabby7.stellarodyssey.item;

import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.block.ModBlocks;
import net.krabby7.stellarodyssey.entity.ModEntities;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import dev.thomasglasser.sherdsapi.api.SherdsApiDataComponents;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(StellarOdyssey.MOD_ID);

    public static final DeferredItem<Item> ENDER_DRAGON_FRUIT =
            ITEMS.registerItem("ender_dragon_fruit", properties -> new Item(properties) {
            }, new Item.Properties().food(ModFoodProperties.ENDER_DRAGON_FRUIT));

    public static final DeferredItem<Item> DRAGON_CACTUS_SEEDS = ITEMS.register("dragon_cactus_seeds",
            () -> new ItemNameBlockItem(ModBlocks.DRAGON_CACTUS.get(), new Item.Properties()));

    public static final DeferredItem<Item> SOLAR_POTTERY_SHERD = ITEMS.register("solar_pottery_sherd",
            () -> new Item(new Item.Properties()
                    .component(SherdsApiDataComponents.SHERD_PATTERN,
                            ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID,
                                    "textures/entity/decorated_pot/solar_pottery_pattern.png"))
            ));

    public static final DeferredItem<Item> MUMMY_ENDERMAN_SPAWN_EGG = ITEMS.register("mummy_enderman_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.MUMMY_ENDERMAN, 0x000000, 0x4f0b0b,
                    new Item.Properties()));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}