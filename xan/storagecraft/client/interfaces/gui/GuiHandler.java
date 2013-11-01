package xan.storagecraft.client.interfaces.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xan.storagecraft.StorageCraft;
import xan.storagecraft.client.interfaces.container.ContainerSC;
import xan.storagecraft.tileentity.TileEntityDiamondChest;
import xan.storagecraft.tileentity.TileEntityGoldChest;
import xan.storagecraft.tileentity.TileEntityIronChest;
import xan.storagecraft.tileentity.TileEntitySC;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiHandler implements IGuiHandler{

	public GuiHandler() {
		NetworkRegistry.instance().registerGuiHandler(StorageCraft.instance, this);
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te;
		switch(ID){
		case 0:
			te = world.getBlockTileEntity(x, y, z);
			if(te != null && te instanceof TileEntityIronChest){
				return new ContainerSC(player.inventory, (TileEntitySC)te);
			}
			break;
		case 1:
			te = world.getBlockTileEntity(x, y, z);
			if(te != null && te instanceof TileEntityGoldChest){
				return new ContainerSC(player.inventory, (TileEntitySC)te);
			}
			break;
		case 2:
			te = world.getBlockTileEntity(x, y, z);
			if(te != null && te instanceof TileEntityDiamondChest){
				return new ContainerSC(player.inventory, (TileEntitySC)te);
			}
			break;
		}
		
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te;
		switch(ID){
		case 0:
			te = world.getBlockTileEntity(x, y, z);
			if(te != null && te instanceof TileEntitySC){
				return new GuiIronChest(player.inventory, (TileEntitySC)te);
			}
			break;
		case 1:
			te = world.getBlockTileEntity(x, y, z);
			if(te != null && te instanceof TileEntitySC){
				return new GuiGoldChest(player.inventory, (TileEntitySC)te);
			}
			break;
		case 2:
			te = world.getBlockTileEntity(x, y, z);
			if(te != null && te instanceof TileEntityGoldChest){
				return new GuiDiamondChest(player.inventory, (TileEntitySC)te);
			}
			break;
		}
		
		return null;
	}

}
