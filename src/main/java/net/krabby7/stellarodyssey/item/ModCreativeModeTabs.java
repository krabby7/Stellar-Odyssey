package net.krabby7.stellarodyssey.item;

import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, StellarOdyssey.MOD_ID);

    public static final Supplier<CreativeModeTab> STELLAR_ODYSSEY_TAB = CREATIVE_MODE_TAB.register("stellar_odyssey_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.STARDUST.get()))
                    .title(Component.translatable("creativetab.stellarodyssey.stellarodyssey"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModBlocks.STARDUST);
                        output.accept(ModBlocks.SUS_STARDUST);
                        output.accept(ModBlocks.STARSTONE);
                        output.accept(ModBlocks.CUT_STARSTONE);
                        output.accept(ModBlocks.CUT_STARSTONE_SLAB);
                        output.accept(ModBlocks.CUT_STARSTONE_STAIRS);
                        output.accept(ModBlocks.CUT_STARSTONE_WALL);
                        output.accept(ModBlocks.CHISELED_STARSTONE);
                        output.accept(ModBlocks.STARSTONE_BRICKS);
                        output.accept(ModBlocks.STARSTONE_BRICKS_SLAB);
                        output.accept(ModBlocks.STARSTONE_BRICKS_STAIRS);
                        output.accept(ModBlocks.STARSTONE_BRICKS_WALL);
                        output.accept(ModBlocks.STARGLASS);
                        output.accept(ModBlocks.STARGLASS_PANE);

                        output.accept(ModBlocks.MOONDUST);
                        output.accept(ModBlocks.SUS_MOONDUST);
                        output.accept(ModBlocks.MOONSTONE);
                        output.accept(ModBlocks.CUT_MOONSTONE);
                        output.accept(ModBlocks.CUT_MOONSTONE_SLAB);
                        output.accept(ModBlocks.CUT_MOONSTONE_STAIRS);
                        output.accept(ModBlocks.CUT_MOONSTONE_WALL);
                        output.accept(ModBlocks.CHISELED_MOONSTONE);
                        output.accept(ModBlocks.MOONSTONE_BRICKS);
                        output.accept(ModBlocks.MOONSTONE_BRICKS_SLAB);
                        output.accept(ModBlocks.MOONSTONE_BRICKS_STAIRS);
                        output.accept(ModBlocks.MOONSTONE_BRICKS_WALL);
                        output.accept(ModBlocks.MOONGLASS);
                        output.accept(ModBlocks.MOONGLASS_PANE);

                        output.accept(ModItems.DRAGON_CACTUS_SEEDS);
                        output.accept(ModItems.ENDER_DRAGON_FRUIT);
                        output.accept(ModBlocks.SUNBURNT_SHRUB);
                        output.accept(ModBlocks.SUNCCULENT);

                        output.accept(ModItems.MUMMY_ENDERMAN_SPAWN_EGG);
                        output.accept(ModItems.OVERSEER_SPAWN_EGG);

                        output.accept(ModItems.SOLAR_POTTERY_SHERD);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}