package xan.storagecraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntityLiquidStorageTank extends TileEntity implements IFluidHandler{

	public FluidTank tank;
	
	public TileEntityLiquidStorageTank() {
		tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 8);
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		int amount = tank.fill(resource, doFill);
		//System.out.println(amount);
		if(amount > 0 && doFill){
			//Update rendering code here when implemented
		}
		
		return amount;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
			return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		
		FluidStack amount = tank.drain(maxDrain, doDrain);
		
		if(amount != null && doDrain){
			//Update rendering code here when implemented
		}
		
		return amount;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		FluidStack fluidStack = null;
		if(tank.getFluid() != null){
			fluidStack = tank.getFluid().copy();
		}
		
		return new FluidTankInfo[]{ new FluidTankInfo(fluidStack, tank.getCapacity()) };
	}
	
	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		
		FluidStack fluid = tank.getFluid();
		par1nbtTagCompound.setBoolean("tankHasFluid", fluid != null);
		if(fluid != null){
			par1nbtTagCompound.setInteger("liquidID", fluid.fluidID);
			par1nbtTagCompound.setInteger("amount", fluid.amount);
		}
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		
		if(par1nbtTagCompound.getBoolean("tankHasFluid")){
			tank.setFluid(new FluidStack(par1nbtTagCompound.getInteger("liquidID"), par1nbtTagCompound.getInteger("amount")));
		}
		else
		{
			tank.setFluid(null);
		}
	}
	
	public void getTextureFromId(int id){
		
	}

}
