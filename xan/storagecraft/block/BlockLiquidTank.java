package xan.storagecraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import xan.storagecraft.lib.Reference;
import xan.storagecraft.lib.RenderIDs;
import xan.storagecraft.network.PacketHandler;
import xan.storagecraft.tileentity.TileEntityLiquidStorageTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLiquidTank extends Block{

	private Icon innerIcon;
	
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
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return RenderIDs.TANK_ID;
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		
		if(meta == 1){
			return innerIcon;
		}
		
		return blockIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		blockIcon = par1IconRegister.registerIcon(Reference.MOD_ID +":tank");
		innerIcon = par1IconRegister.registerIcon(Reference.MOD_ID +":tank_inner");
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityLiquidStorageTank();
	}
	
	@Override
	public void updateTick(World par1World, int x, int y, int z, Random par5Random) {
		super.updateTick(par1World, x, y, z, par5Random);
		Minecraft.getMinecraft().renderGlobal.markBlockForUpdate(x, y, z);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float clickX, float clickY, float clickZ) {
		world.scheduleBlockUpdate(x, y, z, 0, 1);
		ItemStack playerItem = player.inventory.getCurrentItem();
		if(playerItem != null){
			TileEntityLiquidStorageTank tank = (TileEntityLiquidStorageTank)world.getBlockTileEntity(x, y, z);
			FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(player.getCurrentEquippedItem());
			if(liquid != null){
				int amount = tank.fill(ForgeDirection.UNKNOWN, liquid, false);
				if(amount == liquid.amount){
					tank.fill(ForgeDirection.UNKNOWN, liquid, true);
					if(!player.capabilities.isCreativeMode){
						player.inventory.setInventorySlotContents(player.inventory.currentItem, removeItem(playerItem));
						return true;
					}
					
					return true;
				}
				else{
					return true;
				}
			}
			else if (FluidContainerRegistry.isBucket(playerItem)){
				FluidTankInfo[] tankInfo = tank.getTankInfo(ForgeDirection.UNKNOWN);
				FluidStack fillFluid = tankInfo[0].fluid;
				ItemStack stack = FluidContainerRegistry.fillFluidContainer(fillFluid, playerItem);
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
