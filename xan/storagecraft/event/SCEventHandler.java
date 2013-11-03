package xan.storagecraft.event;

import xan.storagecraft.lib.BlockIDs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class SCEventHandler {

	@ForgeSubscribe
	public void playerInteract(PlayerInteractEvent event){
		if(event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK){
			if(event.entityPlayer.inventory.getCurrentItem() == null)
				return;
			ItemStack playerItem = event.entityPlayer.inventory.getCurrentItem();
			if(playerItem.getItem() == Item.bucketMilk && event.entityPlayer.isSneaking()){
				if(!event.entityPlayer.capabilities.isCreativeMode){
					event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, new ItemStack(Item.bucketEmpty, 1));
				}
				
				switch(ForgeDirection.getOrientation(event.face)){
					case UP:
						event.entityPlayer.worldObj.setBlock(event.x, event.y + 1, event.z, BlockIDs.LIQUID_TANK_ID + 1);
						break;
					case DOWN:
						event.entityPlayer.worldObj.setBlock(event.x, event.y - 1, event.z, BlockIDs.LIQUID_TANK_ID + 1);
						break;
					case EAST:
						event.entityPlayer.worldObj.setBlock(event.x + 1, event.y, event.z, BlockIDs.LIQUID_TANK_ID + 1);
						break;
					case WEST:
						event.entityPlayer.worldObj.setBlock(event.x - 1, event.y, event.z, BlockIDs.LIQUID_TANK_ID + 1);
						break;
					case NORTH:
						event.entityPlayer.worldObj.setBlock(event.x, event.y, event.z - 1, BlockIDs.LIQUID_TANK_ID + 1);
						break;
					case SOUTH:
						event.entityPlayer.worldObj.setBlock(event.x, event.y, event.z + 1, BlockIDs.LIQUID_TANK_ID + 1);
						break;
					default:
						System.out.println("Unknown Forge Direction");
						break;
				}
			}
			else if(playerItem.getItem() == Item.bucketEmpty && event.entityPlayer.isSneaking()){
					switch(ForgeDirection.getOrientation(event.face)){
					case UP:
						if(event.entityPlayer.worldObj.getBlockId(event.x, event.y + 1, event.z) == BlockIDs.LIQUID_TANK_ID +1){
							event.entityPlayer.worldObj.setBlockToAir(event.x, event.y + 1, event.z);
							if(!event.entityPlayer.capabilities.isCreativeMode)
								event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, new ItemStack(Item.bucketMilk, 1));
						}
						break;
					case DOWN:
						if(event.entityPlayer.worldObj.getBlockId(event.x, event.y - 1, event.z) == BlockIDs.LIQUID_TANK_ID +1){
							event.entityPlayer.worldObj.setBlockToAir(event.x, event.y - 1, event.z);
							if(!event.entityPlayer.capabilities.isCreativeMode)
								event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, new ItemStack(Item.bucketMilk, 1));
						}
						break;
					case EAST:
						if(event.entityPlayer.worldObj.getBlockId(event.x + 1, event.y, event.z) == BlockIDs.LIQUID_TANK_ID +1){
							event.entityPlayer.worldObj.setBlockToAir(event.x + 1, event.y, event.z);
							if(!event.entityPlayer.capabilities.isCreativeMode)
								event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, new ItemStack(Item.bucketMilk, 1));
						}
						break;
					case WEST:
						if(event.entityPlayer.worldObj.getBlockId(event.x - 1, event.y, event.z) == BlockIDs.LIQUID_TANK_ID +1){
							event.entityPlayer.worldObj.setBlockToAir(event.x - 1, event.y, event.z);
							if(!event.entityPlayer.capabilities.isCreativeMode)
								event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, new ItemStack(Item.bucketMilk, 1));
						}
						break;
					case NORTH:
						if(event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z - 1) == BlockIDs.LIQUID_TANK_ID +1){
							event.entityPlayer.worldObj.setBlockToAir(event.x, event.y, event.z - 1);
							if(!event.entityPlayer.capabilities.isCreativeMode)
								event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, new ItemStack(Item.bucketMilk, 1));
						}
						break;
					case SOUTH:
						if(event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z + 1) == BlockIDs.LIQUID_TANK_ID +1){
							event.entityPlayer.worldObj.setBlockToAir(event.x, event.y, event.z + 1);
							if(!event.entityPlayer.capabilities.isCreativeMode)
								event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, new ItemStack(Item.bucketMilk, 1));
						}
						break;
					default:
						System.out.println("Unknown Forge Direction");
						break;
				}
			}
		}
	}
	
}
