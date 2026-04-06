package net.krabby7.stellarodyssey.item;
import net.krabby7.stellarodyssey.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier CELESTEEL = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_BLACK_OPAL_TOOL,
            825, 7f, 2.5f, 12,
            () -> Ingredient.of(ModItems.CELESTEEL_INGOT.get()));
}