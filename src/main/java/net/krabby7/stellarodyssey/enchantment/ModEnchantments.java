package net.krabby7.stellarodyssey.enchantment;
import net.krabby7.stellarodyssey.StellarOdyssey;
import net.krabby7.stellarodyssey.enchantment.custom.DislocatingEnchantmentEffect;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.Enchantments;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> DISLOCATING = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(StellarOdyssey.MOD_ID, "dislocating"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantment = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);
        HolderGetter<Item> holdergetter2 = context.lookup(Registries.ITEM);

        register(context, DISLOCATING, Enchantment.enchantment(Enchantment.definition(holdergetter2.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                        holdergetter2.getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE),
                        1, 3, Enchantment.dynamicCost(10, 20),
                        Enchantment.dynamicCost(60, 20), 8,
                        new EquipmentSlotGroup[]{EquipmentSlotGroup.ANY}))
                        //.exclusiveWith((HolderSet<Enchantment>) enchantment.getOrThrow(Enchantments.THORNS))
                        .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.VICTIM,
                        EnchantmentTarget.ATTACKER, new DislocatingEnchantmentEffect(1)));
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }
}