package xan.storagecraft.block;

import static net.minecraftforge.common.ForgeDirection.DOWN;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import xan.storagecraft.StorageCraft;
import xan.storagecraft.tileentity.TileEntityDiamondChest;
import xan.storagecraft.tileentity.TileEntityGoldChest;
import xan.storagecraft.tileentity.TileEntityIronChest;
import xan.storagecraft.tileentity.TileEntitySC;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChestMulti extends Block{
	

	public BlockChestMulti(int par1) {
		super(par1, Material.iron);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean hasTileEntity(int meta) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		switch(metadata % 4){
		case 0:
			return new TileEntityIronChest();
		case 1:
			return new TileEntityGoldChest();
		case 2:
			return new TileEntityDiamondChest();
		default:
			return new TileEntitySC();
		}
		
	}
	
	@Override
	public int damageDropped(int par1) {
		return par1 % 4;
	}
	
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconReg){
		icons = new Icon[4];
		icons[0] = iconReg.registerIcon("iron_block");
		icons[1] = iconReg.registerIcon("gold_block");
		icons[2] = iconReg.registerIcon("diamond_block");
		icons[3] = iconReg.registerIcon("quartz_block_top");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int meta){
		return icons[meta % 4];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List list){
		for(int i = 0; i < 4; i++){
			list.add(new ItemStack(par1, 1, i));
		}
	}
	
	 /**
     * Called upon block activation (right click on the block.)
     */
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
		if(!world.isRemote){
			if(world.getBlockMetadata(x, y, z) % 4 == 0 && !world.isBlockSolidOnSide(x, y + 1, z, DOWN))
				FMLNetworkHandler.openGui(player, StorageCraft.instance, 0, world, x, y, z);
			else if(world.getBlockMetadata(x, y, z) % 4 == 1 && !world.isBlockSolidOnSide(x, y + 1, z, DOWN))
				FMLNetworkHandler.openGui(player, StorageCraft.instance, 1, world, x, y, z);
			else if(world.getBlockMetadata(x, y, z) % 4 == 2 && !world.isBlockSolidOnSide(x, y + 1, z, DOWN))
				FMLNetworkHandler.openGui(player, StorageCraft.instance, 2, world, x, y, z);
		}
		
		return true;
    }
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int meta) {
		
		TileEntity te = world.getBlockTileEntity(x, y, z);
		
		if(te != null && te instanceof IInventory){
			IInventory inventory = (IInventory)te;
			
			for(int i = 0; i < inventory.getSizeInventory(); i++){
				ItemStack stack = inventory.getStackInSlotOnClosing(i);
				
				if(stack != null){
					float spawnX = x + world.rand.nextFloat();
					float spawnY = y + world.rand.nextFloat();
					float spawnZ = z + world.rand.nextFloat();
					
					EntityItem droppedItem = new EntityItem(world, spawnX, spawnY, spawnZ, stack);
					
					float mult = 0.05f;
					
					droppedItem.motionX = (-0.5 + world.rand.nextFloat()) * mult;
					droppedItem.motionY = (4 + world.rand.nextFloat()) * mult;
					droppedItem.motionZ = (-0.5 + world.rand.nextFloat()) * mult;
					
					world.spawnEntityInWorld(droppedItem);
				}
			}
		}
		
		super.breakBlock(world, x, y, z, id, meta);
	}
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		
		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLivingBase, par6ItemStack);
		
        int rotate = ((MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, (rotate * 4) + par1World.getBlockMetadata(par2, par3, par4), 3);
	}
}
