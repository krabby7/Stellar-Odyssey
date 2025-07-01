package net.krabby7.stellarodyssey.worldgen.tree;

import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower EBONY = new TreeGrower(StellarOdyssey.MOD_ID + ":ebony",
            Optional.empty(), Optional.of(ModConfiguredFeatures.EBONY_KEY), Optional.empty());
}
