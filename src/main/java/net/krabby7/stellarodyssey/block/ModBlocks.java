package net.krabby7.stellarodyssey.block;

import com.mojang.serialization.MapCodec;
import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.block.custom.DragonCactusBlock;
import net.krabby7.stellarodyssey.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(StellarOdyssey.MOD_ID);

    public static final DeferredBlock<Block> STARDUST = registerBlock("stardust",
            () -> new FallingBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.SAND).lightLevel(p_50755_ -> 7)) {
                @Override
                protected MapCodec<? extends FallingBlock> codec() {
                    return null;
                }
            });


    public static final DeferredBlock<Block> SUS_STARDUST = registerBlock("sus_stardust",
            () -> new BrushableBlock(STARDUST.get(), SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND_COMPLETED,
                    BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.25F)
                            .sound(SoundType.SUSPICIOUS_SAND).pushReaction(PushReaction.DESTROY).lightLevel(p_50755_ -> 7)));

    public static final DeferredBlock<Block> STARSTONE = registerBlock("starstone",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(0.8F).lightLevel(p_50755_ -> 4))  {
                @Override
                protected MapCodec<? extends Block> codec() {
                    return null;
                }
            });

    public static final DeferredBlock<Block> STARGLASS = registerBlock("starglass",
            () -> new TransparentBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).lightLevel(p_50755_ -> 7)) {
            });

    public static final DeferredBlock<Block> STARGLASS_PANE = registerBlock("starglass_pane",
            () -> new IronBarsBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE).lightLevel(p_50755_ -> 7)) {
            });

    public static final DeferredBlock<Block> CUT_STARSTONE = registerBlock("cut_starstone",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(0.8F).lightLevel(p_50755_ -> 4))  {
                @Override
                protected MapCodec<? extends Block> codec() {
                    return null;
                }
            });

    public static final DeferredBlock<Block> CHISELED_STARSTONE = registerBlock("chiseled_starstone",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(0.8F).lightLevel(p_50755_ -> 4))  {
                @Override
                protected MapCodec<? extends Block> codec() {
                    return null;
                }
            });

    public static final DeferredBlock<Block> STARSTONE_BRICKS = registerBlock("starstone_bricks",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(0.8F).lightLevel(p_50755_ -> 4))  {
                @Override
                protected MapCodec<? extends Block> codec() {
                    return null;
                }
            });

    public static final DeferredBlock<Block> STARSTONE_BRICKS_SLAB = registerBlock("starstone_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(0.8F).lightLevel(p_50755_ -> 4))  {
            });

    public static final DeferredBlock<Block> STARSTONE_BRICKS_STAIRS = registerBlock("starstone_bricks_stairs",
            () -> new StairBlock(ModBlocks.STARSTONE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(0.8F).lightLevel(p_50755_ -> 4)));

    public static final DeferredBlock<Block> STARSTONE_BRICKS_WALL = registerBlock("starstone_bricks_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(0.8F).lightLevel(p_50755_ -> 4))  {
            });


    public static final DeferredBlock<Block> CUT_STARSTONE_SLAB = registerBlock("cut_starstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(0.8F).lightLevel(p_50755_ -> 4))  {
            });

    public static final DeferredBlock<Block> CUT_STARSTONE_STAIRS = registerBlock("cut_starstone_stairs",
            () -> new StairBlock(ModBlocks.CUT_STARSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(0.8F).lightLevel(p_50755_ -> 4)));

    public static final DeferredBlock<Block> CUT_STARSTONE_WALL = registerBlock("cut_starstone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(0.8F).lightLevel(p_50755_ -> 4))  {
            });

    public static final DeferredBlock<Block> SUNBURNT_SHRUB = registerBlock("sunburnt_shrub",
            () -> new BushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FERN)) {
                @Override
                protected MapCodec<? extends BushBlock> codec() {
                    return null;
                }
                @Override
                protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
                    return state.is(BlockTags.SAND) || state.getBlock() instanceof FarmBlock;
                }
            });
    public static final DeferredBlock<Block> POTTED_SUNBURNT_SHRUB = BLOCKS.register("potted_sunburnt_shrub",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), SUNBURNT_SHRUB, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM)));


    public static final DeferredBlock<Block> DRAGON_CACTUS = BLOCKS.register("dragon_cactus",
            () -> new DragonCactusBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}