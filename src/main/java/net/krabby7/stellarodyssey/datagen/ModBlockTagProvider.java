package net.krabby7.stellarodyssey.datagen;

import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, StellarOdyssey.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.STARSTONE.get())
                .add(ModBlocks.CUT_STARSTONE.get())
                .add(ModBlocks.CHISELED_STARSTONE.get())
                .add(ModBlocks.STARSTONE_BRICKS.get())
                .add(ModBlocks.STARSTONE_BRICKS_SLAB.get())
                .add(ModBlocks.STARSTONE_BRICKS_STAIRS.get())
                .add(ModBlocks.STARSTONE_BRICKS_WALL.get())
                .add(ModBlocks.CUT_STARSTONE_SLAB.get())
                .add(ModBlocks.CUT_STARSTONE_STAIRS.get())
                .add(ModBlocks.CUT_STARSTONE_WALL.get());




        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.STARDUST.get());

        this.tag(BlockTags.SAND)
                .add(ModBlocks.STARDUST.get());



        //this.tag(BlockTags.NEEDS_IRON_TOOL)


        //this.tag(BlockTags.NEEDS_DIAMOND_TOOL)


        tag(BlockTags.WALLS).add(ModBlocks.STARSTONE_BRICKS_WALL.get());

        //this.tag(BlockTags.LOGS_THAT_BURN)
    }
}