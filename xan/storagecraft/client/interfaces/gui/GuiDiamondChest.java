package xan.storagecraft.client.interfaces.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import xan.storagecraft.client.interfaces.container.ContainerSC;
import xan.storagecraft.lib.Reference;
import xan.storagecraft.tileentity.TileEntitySC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDiamondChest extends GuiContainer{
	
	public GuiDiamondChest(InventoryPlayer invPlayer, TileEntitySC chest) {
		super(new ContainerSC(invPlayer, chest));
		xSize = 248;
		ySize = 256;
	}

	private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/DiamondChestGUI.png");
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1, 1, 1, 1);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("Diamond Chest", 8, 2, 0x404040);
		fontRenderer.drawString("Inv.", 20, 174, 0x404040);
	}
}
