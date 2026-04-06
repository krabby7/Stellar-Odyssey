package net.krabby7.stellarodyssey.item;

import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.block.ModBlocks;
import net.krabby7.stellarodyssey.entity.ModEntities;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import dev.thomasglasser.sherdsapi.api.SherdsApiDataComponents;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(StellarOdyssey.MOD_ID);

    public static final DeferredItem<Item> CELESTEEL_INGOT = ITEMS.registerSimpleItem("celesteel_ingot");
    public static final DeferredItem<Item> RAW_CELESTEEL =
            ITEMS.registerItem("raw_celesteel", Item::new, new Item.Properties());
    public static final DeferredItem<Item> CELESTEEL_NUGGET = ITEMS.registerSimpleItem("celesteel_nugget");

    public static final DeferredItem<Item> CELESTEEL_SWORD = ITEMS.register("celesteel_sword",
            () -> new SwordItem(ModToolTiers.CELESTEEL,
                    new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.CELESTEEL, 3, -2.4f))));
    public static final DeferredItem<Item> CELESTEEL_PICKAXE = ITEMS.register("celesteel_pickaxe",
            () -> new PickaxeItem(ModToolTiers.CELESTEEL,
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.CELESTEEL, 1, -2.8f))));
    public static final DeferredItem<Item> CELESTEEL_SHOVEL = ITEMS.register("celesteel_shovel",
            () -> new ShovelItem(ModToolTiers.CELESTEEL,
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.CELESTEEL, 1.5f, -3.0f))));
    public static final DeferredItem<Item> CELESTEEL_AXE = ITEMS.register("celesteel_axe",
            () -> new AxeItem(ModToolTiers.CELESTEEL,
                    new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.CELESTEEL, 6, -3.2f))));
    public static final DeferredItem<Item> CELESTEEL_HOE = ITEMS.register("celesteel_hoe",
            () -> new HoeItem(ModToolTiers.CELESTEEL,
                    new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.CELESTEEL, 0, -3.0f))));

    public static final DeferredItem<Item> CELESTEEL_HELMET = ITEMS.register("celesteel_helmet",
            () -> new ArmorItem(ModArmorMaterials.CELESTEEL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(22))));
    public static final DeferredItem<Item> CELESTEEL_CHESTPLATE = ITEMS.register("celesteel_chestplate",
            () -> new ArmorItem(ModArmorMaterials.CELESTEEL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(22))));
    public static final DeferredItem<Item> CELESTEEL_LEGGINGS = ITEMS.register("celesteel_leggings",
            () -> new ArmorItem(ModArmorMaterials.CELESTEEL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(22))));
    public static final DeferredItem<Item> CELESTEEL_BOOTS = ITEMS.register("celesteel_boots",
            () -> new ArmorItem(ModArmorMaterials.CELESTEEL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(22))));

    public static final DeferredItem<Item> ENDER_DRAGON_FRUIT =
            ITEMS.registerItem("ender_dragon_fruit", properties -> new Item(properties) {
            }, new Item.Properties().food(ModFoodProperties.ENDER_DRAGON_FRUIT));

    public static final DeferredItem<Item> DRAGON_CACTUS_SEEDS = ITEMS.register("dragon_cactus_seeds",
            () -> new ItemNameBlockItem(ModBlocks.DRAGON_CACTUS.get(), new Item.Properties()));

    public static final DeferredItem<Item> SOLAR_POTTERY_SHERD = ITEMS.register("solar_pottery_sherd",
            () -> new Item(new Item.Properties()
                    .component(SherdsApiDataComponents.SHERD_PATTERN,
                            ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID,
                                    "solar_pottery_pattern"))
            ));


    public static final DeferredItem<Item> LUNAR_POTTERY_SHERD = ITEMS.register("lunar_pottery_sherd",
            () -> new Item(new Item.Properties()
                    .component(SherdsApiDataComponents.SHERD_PATTERN,
                            ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID,
                                    "lunar_pottery_pattern"))
            ));

    public static final DeferredItem<Item> CONSTELLATION_POTTERY_SHERD = ITEMS.register("constellation_pottery_sherd",
            () -> new Item(new Item.Properties()
                    .component(SherdsApiDataComponents.SHERD_PATTERN,
                            ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID,
                                    "constellation_pottery_pattern"))
            ));


    public static final DeferredItem<Item> MUMMY_ENDERMAN_SPAWN_EGG = ITEMS.register("mummy_enderman_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.MUMMY_ENDERMAN, 0x000000, 0x4f0b0b,
                    new Item.Properties()));

    public static final DeferredItem<Item> OVERSEER_SPAWN_EGG = ITEMS.register("overseer_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.OVERSEER, 0x659b7d, 0x1e4835,
                    new Item.Properties()));

    public static final DeferredItem<Item> NOVA_TICK_SPAWN_EGG = ITEMS.register("nova_tick_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.NOVA_TICK, 0x3b2e48, 0xffa600,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}