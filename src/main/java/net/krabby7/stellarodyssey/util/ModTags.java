package net.krabby7.stellarodyssey.util;

import net.krabby7.stellarodyssey.StellarOdyssey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> INCORRECT_FOR_BLACK_OPAL_TOOL = createTag("incorrect_for_celesteel_tool");
        public static final TagKey<Block> NEEDS_BLACK_OPAL_TOOL = createTag("needs_celesteel_tool");


        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID, name));
        }
    }
}