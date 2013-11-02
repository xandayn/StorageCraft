package xan.storagecraft.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import xan.storagecraft.client.interfaces.container.ContainerSC;
import xan.storagecraft.lib.Reference;
import xan.storagecraft.tileentity.TileEntityQuartzChest;
import xan.storagecraft.tileentity.TileEntitySC;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		ByteArrayDataInput reader = ByteStreams.newDataInput(packet.data);
		EntityPlayer entityPlayer = (EntityPlayer)player;
		Container cont = entityPlayer.openContainer;
		byte packetID = reader.readByte();
		
		switch (packetID){
		case 0:
			int playersUsing = reader.readInt();
			float angle = reader.readFloat();
			if(cont != null && cont instanceof ContainerSC){
				TileEntitySC te = ((ContainerSC)cont).getTileEntity();
				te.numUsingPlayers = playersUsing;
				te.lidAngle = angle;
			}
			break;
		case 1:
			byte b = reader.readByte();
			if(cont != null && cont instanceof ContainerSC){
				TileEntitySC te = ((ContainerSC)cont).getTileEntity();
				if(te != null && te instanceof TileEntityQuartzChest){
					((TileEntityQuartzChest)te).recieveTabPacketEvent((int)b);
				}
			}
			break;
		}
		
		
	}

	public static void sendLidAngle(TileEntitySC tileEntitySC) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);
		
		try {
			dataStream.writeByte((byte)0);
			dataStream.writeInt(tileEntitySC.numUsingPlayers);
			dataStream.writeFloat(tileEntitySC.lidAngle);
			
			PacketDispatcher.sendPacketToAllAround(tileEntitySC.xCoord, tileEntitySC.yCoord, tileEntitySC.zCoord, 66, tileEntitySC.getWorldObj().provider.dimensionId, PacketDispatcher.getPacket(Reference.CHANNEL, byteStream.toByteArray()));
		}catch (IOException e){
			System.err.append("Error sending Lid Angle Packet");
		}
	}

	public static void sendButton(byte i) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);
		
		try {
			dataStream.writeByte((byte)1);
			dataStream.writeByte(i);
			
			PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(Reference.CHANNEL, byteStream.toByteArray()));
		}catch (IOException e){
			System.err.append("Failed to send Quartz Chest tab packet");
		}
	}

}
