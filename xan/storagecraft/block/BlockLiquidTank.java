package xan.storagecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import xan.storagecraft.tileentity.TileEntityLiquidStorageTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLiquidTank extends Block{

	public BlockLiquidTank(int par1) {
		super(par1, Material.glass);
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		blockIcon = par1IconRegister.registerIcon("glass");
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityLiquidStorageTank();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float clickX, float clickY, float clickZ) {
		
		ItemStack playerItem = player.inventory.getCurrentItem();
		if(playerItem != null){
			FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(player.getCurrentEquippedItem());
			TileEntityLiquidStorageTank tank = (TileEntityLiquidStorageTank)world.getBlockTileEntity(x, y, z);
			if(liquid != null){
				int amount = tank.fill(ForgeDirection.UNKNOWN, liquid, false);
				if(amount == liquid.amount){
					tank.fill(ForgeDirection.UNKNOWN, liquid, true);
					if(!player.capabilities.isCreativeMode){
						player.inventory.setInventorySlotContents(player.inventory.currentItem, removeItem(playerItem));
						return true;
					}
				}
				else{
					return true;
				}
			}
			else if (FluidContainerRegistry.isBucket(playerItem)){
				FluidTankInfo[] tankInfo = tank.getTankInfo(ForgeDirection.UNKNOWN);
				FluidStack fillFluid = tankInfo[0].fluid;
				ItemStack stack = FluidContainerRegistry.fillFluidContainer(fillFluid, playerItem);
				System.out.println(stack + "1");
				if(stack != null){
					tank.drain(ForgeDirection.UNKNOWN, FluidContainerRegistry.getFluidForFilledItem(stack).amount, true);
					if(!player.capabilities.isCreativeMode){
						if(playerItem.stackSize == 1){
							player.inventory.setInventorySlotContents(player.inventory.currentItem, stack);
						}
						else
						{
							player.inventory.setInventorySlotContents(player.inventory.currentItem, removeItem(playerItem));
							
							if(!player.inventory.addItemStackToInventory(stack)){
								player.dropPlayerItem(stack);
							}
						}
					}
					return true;
				}
				else
				{
					return true;
				}
			}
		}
		
		return false;
	}

	private ItemStack removeItem(ItemStack playerItem) {
		if(playerItem.stackSize == 1){
			if(playerItem.getItem().hasContainerItem()){
				return playerItem.getItem().getContainerItemStack(playerItem);
			}
			else
			{
				return null;
			}
		}
		else
		{
			playerItem.splitStack(1);
			
			return playerItem;
		}
	}
	
}
