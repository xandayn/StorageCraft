package xan.storagecraft.lib;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import xan.storagecraft.block.BlockChestMulti;
import xan.storagecraft.block.BlockLiquidMilk;
import xan.storagecraft.block.BlockLiquidTank;
import xan.storagecraft.block.ChestMultiItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public class Content {

	public static Block chestBlock;
	public static Block tankBlock;
	public static Block milkBlock;
	public static Fluid fluidMilk;
	
	public static void registerBlocks(){
		chestBlock = new BlockChestMulti(BlockIDs.CHEST_MULTI_ID).setUnlocalizedName("blockMultiChestLocal");
		tankBlock = new BlockLiquidTank(BlockIDs.LIQUID_TANK_ID).setUnlocalizedName("xan_liquidTank");
		fluidMilk = new Fluid("milk");

		if(!FluidRegistry.registerFluid(Content.fluidMilk))
			fluidMilk = FluidRegistry.getFluid("milk");
		milkBlock = new BlockLiquidMilk(BlockIDs.LIQUID_TANK_ID + 1, fluidMilk);
		System.out.println(fluidMilk.getBlockID());
		
		FluidContainerRegistry.registerFluidContainer(new FluidStack(fluidMilk, 1000), new ItemStack(Item.bucketMilk), new ItemStack(Item.bucketEmpty));
		
		
		GameRegistry.registerBlock(chestBlock, ChestMultiItemBlock.class, Reference.MOD_ID+chestBlock.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(tankBlock, Reference.MOD_ID+tankBlock.getUnlocalizedName().substring(5));
	}
	
}
