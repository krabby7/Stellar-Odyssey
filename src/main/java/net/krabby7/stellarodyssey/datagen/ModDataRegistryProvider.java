package net.krabby7.stellarodyssey.datagen;

import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.enchantment.ModEnchantments;
import net.krabby7.stellarodyssey.trim.ModTrimMaterials;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDataRegistryProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap)
            .add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap);

    public ModDataRegistryProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(StellarOdyssey.MOD_ID));
    }

}