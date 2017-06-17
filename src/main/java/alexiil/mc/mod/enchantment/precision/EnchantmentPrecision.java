package alexiil.mc.mod.enchantment.precision;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentPrecision extends Enchantment {

    private static final EntityEquipmentSlot[] SLOTS = { //
        EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND //
    };

    public static final EnchantmentPrecision INSTANCE = new EnchantmentPrecision();

    private EnchantmentPrecision() {
        super(Rarity.UNCOMMON, EnumEnchantmentType.DIGGER, SLOTS);
        setRegistryName(ToolModPrecise.MODID, "precision");
        setName("precision");
    }

    /** Returns the minimal value of enchantability needed on the enchantment level passed. */
    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 1 + 10 * (enchantmentLevel - 1);
    }

    /** Returns the maximum value of enchantability nedded on the enchantment level passed. */
    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }

    /** Returns the maximum level that the enchantment can have. */
    @Override
    public int getMaxLevel() {
        return 1;
    }

    /** Determines if this enchantment can be applied to a specific ItemStack. */
    @Override
    public boolean canApply(ItemStack stack) {
        return stack.getItem() == Items.SHEARS ? true : super.canApply(stack);
    }
}
