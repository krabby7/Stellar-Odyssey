package net.krabby7.stellarodyssey.datagen;

import net.krabby7.stellarodyssey.block.ModBlocks;
import net.krabby7.stellarodyssey.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.STARDUST.get());
        dropSelf(ModBlocks.STARSTONE.get());
        dropSelf(ModBlocks.CUT_STARSTONE.get());
        dropSelf(ModBlocks.CHISELED_STARSTONE.get());
        dropSelf(ModBlocks.STARSTONE_BRICKS.get());
        dropSelf(ModBlocks.STARSTONE_BRICKS_SLAB.get());
        dropSelf(ModBlocks.STARSTONE_BRICKS_STAIRS.get());
        dropSelf(ModBlocks.STARSTONE_BRICKS_WALL.get());
        dropSelf(ModBlocks.CUT_STARSTONE_SLAB.get());
        dropSelf(ModBlocks.CUT_STARSTONE_STAIRS.get());
        dropSelf(ModBlocks.CUT_STARSTONE_WALL.get());
        dropSelf(ModBlocks.MOONDUST.get());
        dropSelf(ModBlocks.MOONSTONE.get());
        dropSelf(ModBlocks.CUT_MOONSTONE.get());
        dropSelf(ModBlocks.CHISELED_MOONSTONE.get());
        dropSelf(ModBlocks.MOONSTONE_BRICKS.get());
        dropSelf(ModBlocks.MOONSTONE_BRICKS_SLAB.get());
        dropSelf(ModBlocks.MOONSTONE_BRICKS_STAIRS.get());
        dropSelf(ModBlocks.MOONSTONE_BRICKS_WALL.get());
        dropSelf(ModBlocks.CUT_MOONSTONE_SLAB.get());
        dropSelf(ModBlocks.CUT_MOONSTONE_STAIRS.get());
        dropSelf(ModBlocks.CUT_MOONSTONE_WALL.get());
        dropSelf(ModBlocks.SUNBURNT_SHRUB.get());
        this.add(ModBlocks.POTTED_SUNBURNT_SHRUB.get(), createPotFlowerItemTable(ModBlocks.SUNBURNT_SHRUB));
        dropSelf(ModBlocks.SUNCCULENT.get());
        this.add(ModBlocks.POTTED_SUNCCULENT.get(), createPotFlowerItemTable(ModBlocks.SUNBURNT_SHRUB));

        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.add(ModBlocks.DRAGON_CACTUS.get(), block -> this.applyExplosionDecay(
                block,LootTable.lootTable().withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.DRAGON_CACTUS.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                ).add(LootItem.lootTableItem(ModItems.ENDER_DRAGON_FRUIT.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.DRAGON_CACTUS.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                ).add(LootItem.lootTableItem(ModItems.DRAGON_CACTUS_SEEDS.get()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}