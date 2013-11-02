package xan.storagecraft.client.interfaces.gui;

public class GuiRectangle {

	private int x;
	private int y;
	private int width;
	private int height;
	
	public GuiRectangle(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean inRect(GuiQuartzChest gui, int mouseX, int mouseY){
		
		mouseX -= gui.getLeft();
		mouseY -= gui.getTop();
		
		return x <= mouseX && mouseX <= x + width && y <= mouseY && mouseY <= y + height;
	}
	
	public void draw(GuiQuartzChest gui, int srcX, int srcY){
		gui.drawTexturedModalRect(gui.getLeft() + x, gui.getTop() + y, srcX, srcY, width, height);
	}
	
}
