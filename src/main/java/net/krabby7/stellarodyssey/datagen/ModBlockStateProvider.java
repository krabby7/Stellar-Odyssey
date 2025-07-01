package net.krabby7.stellarodyssey.datagen;

import net.krabby7.stellarodyssey.block.ModBlocks;
import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.block.custom.DragonCactusBlock;
import net.krabby7.stellarodyssey.block.custom.ModCropBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import java.util.function.Function;



import java.awt.*;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, StellarOdyssey.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //blockWithItem(ModBlocks.BLACK_OPAL_BLOCK);

        //blockItem(ModBlocks.STARGLASS);
        //blockItem(ModBlocks.STARGLASS_PANE);

        stairsBlock(((StairBlock) ModBlocks.STARSTONE_BRICKS_STAIRS.get()), blockTexture(ModBlocks.STARSTONE_BRICKS.get()));
        slabBlock(((SlabBlock) ModBlocks.STARSTONE_BRICKS_SLAB.get()), blockTexture(ModBlocks.STARSTONE_BRICKS.get()), blockTexture(ModBlocks.STARSTONE_BRICKS.get()));
        wallBlock(((WallBlock) ModBlocks.STARSTONE_BRICKS_WALL.get()), blockTexture(ModBlocks.STARSTONE_BRICKS.get()));

        stairsBlock(((StairBlock) ModBlocks.CUT_STARSTONE_STAIRS.get()), blockTexture(ModBlocks.CUT_STARSTONE.get()));
        slabBlock(((SlabBlock) ModBlocks.CUT_STARSTONE_SLAB.get()), blockTexture(ModBlocks.CUT_STARSTONE.get()), blockTexture(ModBlocks.CUT_STARSTONE.get()));
        wallBlock(((WallBlock) ModBlocks.CUT_STARSTONE_WALL.get()), blockTexture(ModBlocks.CUT_STARSTONE.get()));

        simpleBlock(ModBlocks.SUNBURNT_SHRUB.get(),
                models().cross(blockTexture(ModBlocks.SUNBURNT_SHRUB.get()).getPath(), blockTexture(ModBlocks.SUNBURNT_SHRUB.get())).renderType("cutout"));
        simpleBlock(ModBlocks.POTTED_SUNBURNT_SHRUB.get(), models().singleTexture("potted_sunburnt_shrub", ResourceLocation.parse("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.SUNBURNT_SHRUB.get())).renderType("cutout"));

        makeBush(((SweetBerryBushBlock) ModBlocks.DRAGON_CACTUS.get()), "dragon_cactus_stage", "dragon_cactus_stage");
    }

    private void leavesBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private void saplingBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlock(deferredBlock.get(), models().cross(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void transparentBlockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("stellarodyssey:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("stellarodyssey:block/" + deferredBlock.getId().getPath() + appendix));
    }


    public void makeBush(SweetBerryBushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }


    private ConfiguredModel[] states(BlockState state, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(DragonCactusBlock.AGE),
                ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID, "block/" + textureName + state.getValue(DragonCactusBlock.AGE))).renderType("cutout"));

        return models;
    }
    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((ModCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID, "block/" + textureName + state.getValue(((ModCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }
}