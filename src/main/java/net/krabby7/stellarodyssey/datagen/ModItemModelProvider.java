package net.krabby7.stellarodyssey.datagen;

import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.block.ModBlocks;
import net.krabby7.stellarodyssey.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, StellarOdyssey.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
       // basicItem(ModItems.BLACK_OPAL.get());

        wallItem(ModBlocks.STARSTONE_BRICKS_WALL, ModBlocks.STARSTONE_BRICKS);
        wallItem(ModBlocks.CUT_STARSTONE_WALL, ModBlocks.CUT_STARSTONE);
        flowerItem(ModBlocks.SUNBURNT_SHRUB);
        basicItem(ModItems.DRAGON_CACTUS_SEEDS.get());
        withExistingParent(ModItems.MUMMY_ENDERMAN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

    public void flowerItem(DeferredBlock<Block> block) {
        this.withExistingParent(block.getId().getPath(), mcLoc("item/generated"))
                .texture("layer0",  ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID,
                        "block/" + block.getId().getPath()));
    }

    private ItemModelBuilder saplingItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID,"block/" + item.getId().getPath()));
    }

    public void buttonItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

}
