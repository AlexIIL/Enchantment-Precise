package alexiil.mc.mod.enchantment.precision;

import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
@Mod(modid = ToolModPrecise.MODID, version = "${version}", acceptedMinecraftVersions = "[1.10.2,)")
public class ToolModPrecise {

    public static final String MODID = "tool_enchantment_precise";
    @Mod.Instance
    public static ToolModPrecise INSTANCE;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        GameRegistry.register(EnchantmentPrecision.INSTANCE);
    }

    @SubscribeEvent
    public static void getBreakSpeed(PlayerEvent.BreakSpeed event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = player.getHeldItemMainhand();
        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentPrecision.INSTANCE, stack);
        if (level > 0 && event.getOriginalSpeed() > 1.0F) {
            IBlockState state = event.getState();
            World world = player.world;
            BlockPos pos = event.getPos();

            float hardness = state.getBlockHardness(world, pos);
            if (hardness > 0 && ForgeHooks.canHarvestBlock(state.getBlock(), player, world, pos)) {
                event.setNewSpeed(29.9F * hardness);
            }
        }
    }
}
