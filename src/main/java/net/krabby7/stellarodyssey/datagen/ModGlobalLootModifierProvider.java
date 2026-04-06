package net.krabby7.stellarodyssey.datagen;
import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.enchantment.ModEnchantments;
import net.krabby7.stellarodyssey.loot.AddItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, StellarOdyssey.MOD_ID);
    }

    @Override
    protected void start() {
//        this.add("chisel_from_jungle_temple",
//                new AddItemModifier(new LootItemCondition[] {
//                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/end_city_treasure")).build()
//                }, Items.ENCHANTED_BOOK));



    }

    @Override
    public String getName() {
        return "GlobalLootModifier";
    }
}