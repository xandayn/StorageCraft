package xan.storagecraft.client.interfaces.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import xan.storagecraft.client.interfaces.container.ContainerSC;
import xan.storagecraft.lib.Reference;
import xan.storagecraft.network.PacketHandler;
import xan.storagecraft.tileentity.TileEntityQuartzChest;
import xan.storagecraft.tileentity.TileEntitySC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiQuartzChest extends GuiContainer{

	private int selectedTab = 1;
	private static final GuiRectangle[] rectangles;
	private TileEntityQuartzChest chest;
	
	public GuiQuartzChest(InventoryPlayer invPlayer, TileEntityQuartzChest chest) {
		super(new ContainerSC(invPlayer, chest));
		this.chest = chest;
		xSize = 247;
		ySize = 255;
	}

	static {
		rectangles = new GuiRectangle[5];
		for(int i = 0; i < rectangles.length; i++){
			rectangles[i] = new GuiRectangle(235, i * 22, 21, 22);
		}
	}
	
	private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/DiamondChestGUI.png");
	private static final ResourceLocation textureOverlay = new ResourceLocation(Reference.MOD_ID, "textures/gui/QuartzChestGUIOverlay.png");
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1, 1);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		Minecraft.getMinecraft().getTextureManager().bindTexture(textureOverlay);
		drawTexturedModalRect(guiLeft + 235, guiTop, 0, 0, 21, 110);
		
		for (int i = 0; i < rectangles.length; i++){
			GuiRectangle rect = rectangles[i];
			int srcX = 0;
			
			if(chest.selectedTab == i){
				srcX = 21;
			}
			
			if(rect.inRect(this, x, y) && i != chest.selectedTab){
				srcX = 42;
			}
			
			rect.draw(this, srcX, i * 22);
		}
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("How'd you get this?", 8, 2, 0x404040);
	}

	protected int getLeft(){
		return guiLeft;
	}
	
	protected int getTop(){
		return guiTop;
	}
	
	@Override
	protected void mouseClicked(int x, int y, int button) {
		super.mouseClicked(x, y, button);
		
		for(int i = 0; i < rectangles.length; i++){
			GuiRectangle rect = rectangles[i];
			
			if(rect.inRect(this, x, y)){
				PacketHandler.sendButton((byte)i);
				break;
			}
		}
		
	}
	
}
