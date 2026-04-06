package net.krabby7.stellarodyssey.datagen;
import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.block.ModBlocks;
import net.krabby7.stellarodyssey.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> STARGLASS_SMELTABLES = List.of(ModBlocks.STARDUST);
        List<ItemLike> MOONGLASS_SMELTABLES = List.of(ModBlocks.MOONDUST);
        List<ItemLike> CELESTEEL_SMELTABLES = List.of(ModItems.RAW_CELESTEEL, ModBlocks.CELESTEEL_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STARSTONE.get())
                .pattern("BB")
                .pattern("BB")
                .define('B', ModBlocks.STARDUST.get())
                .unlockedBy("has_stardust", has(ModBlocks.STARDUST.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CUT_STARSTONE.get(), 4)
                .pattern("BB")
                .pattern("BB")
                .define('B', ModBlocks.STARSTONE.get())
                .unlockedBy("has_starstone", has(ModBlocks.STARSTONE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STARSTONE_BRICKS.get(), 4)
                .pattern("BB")
                .pattern("BB")
                .define('B', ModBlocks.CUT_STARSTONE.get())
                .unlockedBy("has_starstone", has(ModBlocks.STARSTONE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHISELED_STARSTONE.get(), 1)
                .pattern("B")
                .pattern("B")
                .define('B', ModBlocks.CUT_STARSTONE_SLAB.get())
                .unlockedBy("has_cut_starstone", has(ModBlocks.CUT_STARSTONE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STARGLASS_PANE.get(), 16)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModBlocks.STARGLASS.get())
                .unlockedBy("has_starglass", has(ModBlocks.STARGLASS.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MOONSTONE.get())
                .pattern("BB")
                .pattern("BB")
                .define('B', ModBlocks.MOONDUST.get())
                .unlockedBy("has_moondust", has(ModBlocks.MOONDUST.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CUT_MOONSTONE.get(), 4)
                .pattern("BB")
                .pattern("BB")
                .define('B', ModBlocks.MOONSTONE.get())
                .unlockedBy("has_moonstone", has(ModBlocks.MOONSTONE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MOONSTONE_BRICKS.get(), 4)
                .pattern("BB")
                .pattern("BB")
                .define('B', ModBlocks.CUT_MOONSTONE.get())
                .unlockedBy("has_moonstone", has(ModBlocks.MOONSTONE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHISELED_MOONSTONE.get(), 1)
                .pattern("B")
                .pattern("B")
                .define('B', ModBlocks.CUT_MOONSTONE_SLAB.get())
                .unlockedBy("has_cut_moonstone", has(ModBlocks.CUT_MOONSTONE.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MOONGLASS_PANE.get(), 16)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModBlocks.MOONGLASS.get())
                .unlockedBy("has_moonglass", has(ModBlocks.MOONGLASS.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.DRAGON_BREATH, 1)
                .pattern("F")
                .pattern("P")
                .pattern("B")
                .define('B', Items.GLASS_BOTTLE)
                .define('P', Items.BLAZE_POWDER)
                .define('F', ModItems.ENDER_DRAGON_FRUIT)
                .unlockedBy("has_ender_dragon_fruit", has(ModItems.ENDER_DRAGON_FRUIT.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CELESTEEL_NUGGET.get(), 9)
                .requires(ModItems.CELESTEEL_INGOT.get())
                .unlockedBy("has_celesteel_ingot", has(ModItems.CELESTEEL_INGOT.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CELESTEEL_INGOT.get(), 1)
                .requires(ModItems.CELESTEEL_NUGGET.get(), 9)
                .unlockedBy("has_celesteel_nugget", has(ModItems.CELESTEEL_NUGGET.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CELESTEEL_HELMET.get(), 1)
                .pattern("BBB")
                .pattern("B B")
                .define('B', ModItems.CELESTEEL_INGOT.get())
                .unlockedBy("has_celesteel_ingot", has(ModItems.CELESTEEL_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CELESTEEL_LEGGINGS.get(), 1)
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .define('B', ModItems.CELESTEEL_INGOT.get())
                .unlockedBy("has_celesteel_ingot", has(ModItems.CELESTEEL_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CELESTEEL_CHESTPLATE.get(), 1)
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.CELESTEEL_INGOT.get())
                .unlockedBy("has_celesteel_ingot", has(ModItems.CELESTEEL_INGOT.get())).save(pRecipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CELESTEEL_BOOTS.get(), 1)
                .pattern("B B")
                .pattern("B B")
                .define('B', ModItems.CELESTEEL_INGOT.get())
                .unlockedBy("has_celesteel_ingot", has(ModItems.CELESTEEL_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CELESTEEL_SWORD.get(), 1)
                .pattern("B")
                .pattern("B")
                .pattern("S")
                .define('B', ModItems.CELESTEEL_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy("has_celesteel_ingot", has(ModItems.CELESTEEL_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CELESTEEL_SHOVEL.get(), 1)
                .pattern("B")
                .pattern("S")
                .pattern("S")
                .define('B', ModItems.CELESTEEL_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy("has_celesteel_ingot", has(ModItems.CELESTEEL_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CELESTEEL_PICKAXE.get(), 1)
                .pattern("BBB")
                .pattern(" S ")
                .pattern(" S ")
                .define('B', ModItems.CELESTEEL_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy("has_celesteel_ingot", has(ModItems.CELESTEEL_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CELESTEEL_AXE.get(), 1)
                .pattern("BB")
                .pattern("BS")
                .pattern(" S")
                .define('B', ModItems.CELESTEEL_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy("has_celesteel_ingot", has(ModItems.CELESTEEL_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CELESTEEL_HOE.get(), 1)
                .pattern("BB")
                .pattern(" S")
                .pattern(" S")
                .define('B', ModItems.CELESTEEL_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy("has_celesteel_ingot", has(ModItems.CELESTEEL_INGOT.get())).save(pRecipeOutput);


        oreSmelting(pRecipeOutput, STARGLASS_SMELTABLES, RecipeCategory.MISC, ModBlocks.STARGLASS.get(), 0.25f, 200, "starglass");
        oreSmelting(pRecipeOutput, MOONGLASS_SMELTABLES, RecipeCategory.MISC, ModBlocks.MOONGLASS.get(), 0.25f, 200, "moonglass");
        oreSmelting(pRecipeOutput, CELESTEEL_SMELTABLES, RecipeCategory.MISC, ModItems.CELESTEEL_INGOT.get(), 0.25f, 200, "celesteel");
        oreBlasting(pRecipeOutput, CELESTEEL_SMELTABLES, RecipeCategory.MISC, ModItems.CELESTEEL_INGOT.get(), 0.25f, 100, "celesteel");


    stairBuilder(ModBlocks.CUT_STARSTONE_STAIRS.get(), Ingredient.of(ModBlocks.CUT_STARSTONE.get())).group("starstone")
                .unlockedBy("has_starstone", has(ModBlocks.STARSTONE.get())).save(pRecipeOutput);
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_STARSTONE_SLAB.get(), ModBlocks.CUT_STARSTONE.get());

        stairBuilder(ModBlocks.STARSTONE_BRICKS_STAIRS.get(), Ingredient.of(ModBlocks.STARSTONE_BRICKS.get())).group("starstone")
                .unlockedBy("has_starstone", has(ModBlocks.STARSTONE.get())).save(pRecipeOutput);
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.STARSTONE_BRICKS_SLAB.get(), ModBlocks.STARSTONE_BRICKS.get());

        stairBuilder(ModBlocks.CUT_MOONSTONE_STAIRS.get(), Ingredient.of(ModBlocks.CUT_MOONSTONE.get())).group("moonstone")
                .unlockedBy("has_moonstone", has(ModBlocks.MOONSTONE.get())).save(pRecipeOutput);
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_MOONSTONE_SLAB.get(), ModBlocks.CUT_MOONSTONE.get());

        stairBuilder(ModBlocks.MOONSTONE_BRICKS_STAIRS.get(), Ingredient.of(ModBlocks.MOONSTONE_BRICKS.get())).group("moonstone")
            .unlockedBy("has_moonstone", has(ModBlocks.MOONSTONE.get())).save(pRecipeOutput);
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOONSTONE_BRICKS_SLAB.get(), ModBlocks.MOONSTONE_BRICKS.get());

//        pressurePlate(pRecipeOutput, ModBlocks.BLACK_OPAL_PRESSURE_PLATE.get(), ModItems.BLACK_OPAL.get());
//        buttonBuilder(ModBlocks.BLACK_OPAL_BUTTON.get(), Ingredient.of(ModItems.BLACK_OPAL.get())).group("black_opal")
//                .unlockedBy("has_black_opal", has(ModItems.BLACK_OPAL.get())).save(pRecipeOutput);

        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_STARSTONE_WALL.get(), ModBlocks.CUT_STARSTONE.get());
        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.STARSTONE_BRICKS_WALL.get(), ModBlocks.STARSTONE_BRICKS.get());
        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_MOONSTONE_WALL.get(), ModBlocks.CUT_MOONSTONE.get());
        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOONSTONE_BRICKS_WALL.get(), ModBlocks.MOONSTONE_BRICKS.get());

//        doorBuilder(ModBlocks.BLACK_OPAL_DOOR.get(), Ingredient.of(ModItems.BLACK_OPAL.get())).group("black_opal")
//                .unlockedBy("has_black_opal", has(ModItems.BLACK_OPAL.get())).save(pRecipeOutput);
//        trapdoorBuilder(ModBlocks.BLACK_OPAL_TRAPDOOR.get(), Ingredient.of(ModItems.BLACK_OPAL.get())).group("black_opal")
//                .unlockedBy("has_black_opal", has(ModItems.BLACK_OPAL.get())).save(pRecipeOutput);
    }

    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, StellarOdyssey.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}