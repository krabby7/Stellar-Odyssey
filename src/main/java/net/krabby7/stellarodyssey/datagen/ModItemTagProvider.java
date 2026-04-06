package net.krabby7.stellarodyssey.datagen;
import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.block.ModBlocks;
import net.krabby7.stellarodyssey.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                              CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, StellarOdyssey.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.STONE_TOOL_MATERIALS)
                .add(Item.byBlock(ModBlocks.STARSTONE.get()))
                .add(Item.byBlock(ModBlocks.MOONSTONE.get()));

        this.tag(ItemTags.SAND)
                .add(Item.byBlock(ModBlocks.STARDUST.get()))
                .add(Item.byBlock(ModBlocks.MOONDUST.get()));

        this.tag(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(Item.byBlock(ModBlocks.STARSTONE.get()))
                .add(Item.byBlock(ModBlocks.MOONSTONE.get()));

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.CELESTEEL_HELMET.get())
                .add(ModItems.CELESTEEL_CHESTPLATE.get())
                .add(ModItems.CELESTEEL_LEGGINGS.get())
                .add(ModItems.CELESTEEL_BOOTS.get());

        this.tag(ItemTags.TRIM_MATERIALS)
                .add(ModItems.CELESTEEL_INGOT.get());

    }
}