package xan.storagecraft.block;

import xan.storagecraft.lib.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLiquidMilk extends BlockFluidBase{

	@SideOnly(Side.CLIENT)
	public Icon[] icons = new Icon[2];
	
	public BlockLiquidMilk(int id, Fluid fluid) {
		super(id, fluid, Material.water);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icons[0] = par1IconRegister.registerIcon(Reference.MOD_ID+":milk" + "_still");
		icons[1] = par1IconRegister.registerIcon(Reference.MOD_ID+":milk" +"_flow");
	}
	
	@Override
	public Icon getIcon(int par1, int par2) {

		if(par1 == 0 || par1 == 1)
			return icons[0];
		
		return icons[1];
	}
	
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		if(par5Entity instanceof EntityLivingBase){
			par5Entity.motionX *= 0.95D;
			par5Entity.motionZ *= 0.95D;
		}
	}

	@Override
	public FluidStack drain(World world, int x, int y, int z, boolean doDrain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canDrain(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getQuantaValue(IBlockAccess world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return 1000;
	}

	@Override
	public boolean canCollideCheck(int meta, boolean fullHit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMaxRenderHeightMeta() {
		// TODO Auto-generated method stub
		return 0;
	}
}
